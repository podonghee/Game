package com.podong.game.module.schduling.batched;
import com.podong.game.module.schduling.bean.GameSshotVO;
import com.podong.game.module.schduling.dao.BatchMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component("gameSshotBatchClass")
public class GameSshotBatchClass extends BasicClass {
    @Value("${crawling.gameSshotUrl}")
    private String gameSshotUrl;
    @Autowired
    private BatchMapper batchMapper;
    private void gmShInsert() throws IOException {
        Document doc = null;
        String aTag = null;
        String gmId = null;
        GameSshotVO vo = new GameSshotVO();
        for (int i = 1; i < 147; i++) {
            doc = Jsoup.connect(gameSshotUrl + i).get();
            for (Element list : doc.select(".game-thumb-box ul > li")) {
                aTag = list.select("a").attr("href");
                vo.setGameSshotUrl(aTag);
                gmId = aTag.replace("?rts=gmview&gmid=", "");
                gmId = gmId.replace("&tab=sshot", "");
                vo.setGameId(gmId);
                batchMapper.insertGameSshot(vo);
            }
        }
    }
    private void gmShUpdate() throws IOException {
        String description = "";
        String imgUrl = "";
        String gid = "";
        String gmid = "";
        String webUrl = "";
        Document doc = null;
        //크롤링한 스크린샷 리스트를 가지고옴.
        List<GameSshotVO> gameShotList = batchMapper.getSelectShotList();
        int i = 0;
        for(GameSshotVO shvo : gameShotList){
            i=0;
            //shvo.getGameSshotUrl() 에 url이 담겨있음 이것으로 크롤링 시작.
            doc = Jsoup.connect("https://www.gamemeca.com/game.php"+shvo.getGameSshotUrl()).get();
            for (Element list : doc.select(".db_thumlist ul > li")) {
                imgUrl = list.select("img").attr("src");
                description = list.text();
                shvo.setGameSshotImgUrl(imgUrl);
                shvo.setDescription(description);
                if(list.select("li").size() > 1)
                {
                    batchMapper.deleteGameSshot(shvo);
                    batchMapper.insertGameSshot(shvo);
                }
                else
                {
                    batchMapper.updateGameSshot(shvo);
                }
                //shvo.setGameGroupId(i);
                gid = shvo.getGameId();
                gmid = list.select("a").attr("data-gid");
                webUrl = "https://www.gamemeca.com/game.php"+"?rts=gmview&gmid=" + gid + "&tab=sshot&gid=" + gmid;
                //이부분은 chromeDriver 를 사용
                gmShInfoInsert(webUrl,shvo);
                i++;
            }
            System.out.println(i);
        }
    }
    private void gmShInfoInsert(String url ,GameSshotVO shvo){
        boolean flag = true;
        String imgListUrl = "";
        String dataGid = "";
        int liSize = 0;
        WebDriver driver = chromeDriver(url);
        List<WebElement> webElements = null;
        while(flag) {
            //스크린샷 리스트 가져옴.
            webElements = driver.findElements(By.cssSelector("#layer-header-ul > li"));
            for (WebElement wel : webElements) {
                imgListUrl = wel.findElement(By.cssSelector("img")).getAttribute("src");
                shvo.setGameSshotImgUrl(imgListUrl);
                shvo.setGameParentSshotId(shvo.getGameSshotId());
                shvo.setDescription("");
                shvo.setGameSshotUrl("");

                batchMapper.insertTotalGameSshot(shvo);
                dataGid = webElements.get(0).findElement(By.cssSelector("a")).getAttribute("data-idx");
            }
            liSize = webElements.size();
            //만약 스크린샷 li 사이즈가 5가아니면 false 어차피 없으니까 while 을 빠져나가야함.
            if(liSize < 5)
            {
                flag = false;
            }
            // 5개인경우
            else
            {
                //스크린샷 화살표 버튼 클릭 xPath로 태그 가져옴.
                WebElement imgClick = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[2]/div[4]/div[1]/div[3]"));
                //화살표 버튼 클릭.
                imgClick.click();
                //다시 ul li 스크린샷 리스트 불러옴.
                webElements = driver.findElements(By.cssSelector("#layer-header-ul > li"));
                //만약에 버튼 클릭 시 동일한  dataGid 인경우는 while문 빠져나가야함 비교할 필요가 없음.
                if(dataGid.equals(webElements.get(0).findElement(By.cssSelector("a")).getAttribute("data-idx"))){
                    break;
                }
                // 5개보다 작은경우 insert
                if (webElements.size() < 5) {
                    for (WebElement wel : webElements) {
                        imgListUrl = wel.findElement(By.cssSelector("img")).getAttribute("src");
                        shvo.setGameSshotImgUrl(imgListUrl);
                        shvo.setGameParentSshotId(shvo.getGameSshotId());
                        shvo.setDescription("");
                        batchMapper.insertSsohtInfo(shvo);
                    }
                    flag = false;
                }
                //5개인경우는 다시 위에서 인설트 시켜주기 위함.
                else if(webElements.size() == 5)
                {
                    continue;
                }
            }
        }
        //webDriver close
        driver.close();
    }
    public void gameSshotBatch() {
        try {
            //크롤링하여 게임 스크린샷 인설트
            //gmShInsert();
            //크롤링하여 게임 스크린샷 업데이트
            gmShUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void start(){
        gameSshotBatch();
    }

}
