package com.podong.game.module.schduling.batched;

import com.podong.game.module.schduling.bean.GameSshotVO;
import com.podong.game.module.schduling.bean.GameVideoVO;
import com.podong.game.module.schduling.dao.BatchMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component("gameVideoBatchClass")
public class GameVideoBatchClass extends BasicClass {
    @Value("${crawling.gameVideoUrl}")
    private String gameVideoUrl;
    @Autowired
    private BatchMapper batchMapper;

    private void gmVideoInsert() throws IOException {
        Document doc = null;
        String aTag = null;
        String gmId = null;
        GameVideoVO vo = new GameVideoVO();
        for (int i = 1; i < 147; i++) {
            doc = Jsoup.connect(gameVideoUrl + i).get();
            for (Element list : doc.select(".game-thumb-box ul > li")) {
                aTag = list.select("a").attr("href");
                vo.setGameVideoUrl(aTag);
                gmId = aTag.replace("?rts=gmview&gmid=", "");
                gmId = gmId.replace("&tab=movie", "");
                vo.setGameId(gmId);
                batchMapper.insertGameVideo(vo);
            }
        }
    }
    private void gmVideoUpdate() throws IOException {
        String title = "";
        String imgUrl = "";
        String gid = "";
        String gmid = "";
        String webUrl = "";
        Document doc = null;
        //크롤링한 스크린샷 리스트를 가지고옴.
        List<GameVideoVO> gameVideoList = batchMapper.getSelectVideoList();
        int i = 0;
        for(GameVideoVO vivo : gameVideoList){
            i=0;
            //shvo.getGameSshotUrl() 에 url이 담겨있음 이것으로 크롤링 시작.
            doc = Jsoup.connect("https://www.gamemeca.com/game.php"+vivo.getGameVideoUrl()).get();
            for (Element list : doc.select(".db_thumlist ul > li")) {
                imgUrl = list.select("img").attr("src");
                title = list.text();
                vivo.setGameVideoImgUrl(imgUrl);
                vivo.setGameVideoTitle(title);
                if(list.select("li").size() > 1)
                {
                    batchMapper.deleteGameVideo(vivo);
                    batchMapper.insertGameVideo(vivo);
                }
                else
                {
                    batchMapper.updateGameVideo(vivo);
                }
                //shvo.setGameGroupId(i);
                gid = vivo.getGameId();
                gmid = list.select("a").attr("data-gid");
                webUrl = "https://www.gamemeca.com/game.php"+"?rts=gmview&gmid=" + gid + "&tab=movie&gid=" + gmid;
                //이부분은 chromeDriver 를 사용
                gmViInfoInsert(webUrl,vivo);
                i++;
            }
            System.out.println(i);
        }
    }
    private void gmViInfoInsert(String url ,GameVideoVO vivo){
        boolean flag = true;
        String imgListUrl = "";
        String dataGid = "";
        int liSize = 0;
        WebDriver driver = chromeDriver(url);
        List<WebElement> webElements = null;
        while(flag) {
            //동영상 리스트 가져옴.
            webElements = driver.findElements(By.cssSelector("#layer-body-img"));
            for (WebElement wel : webElements) {
                imgListUrl = wel.findElement(By.cssSelector("iframe")).getAttribute("src");
                vivo.setGameVideoImgUrl(imgListUrl);
                vivo.setGameParentVideoId(vivo.getGameVideoId());
                vivo.setDescription(wel.getText());
                vivo.setGameVideoUrl("");

                batchMapper.insertTotalGameVideo(vivo);
                dataGid = webElements.get(0).findElement(By.cssSelector("a")).getAttribute("data-idx");
            }
            liSize = webElements.size();
            //만약 스크린샷 li 사이즈가 5가아니면 false 어차피 없으니까 while 을 빠져나가야함.
            if(liSize < 5)
            {
                flag = false;
                driver.close();
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
                        imgListUrl = wel.findElement(By.cssSelector("iframe")).getAttribute("src");
                        vivo.setGameVideoImgUrl(imgListUrl);
                        vivo.setGameParentVideoId(vivo.getGameVideoId());
                        vivo.setDescription(wel.getText());
                        batchMapper.insertSsohtInfo(vivo);
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
    public void gameVideoBatch() {
        try {
            //크롤링하여 게임 스크린샷 인설트
            //gmVideoInsert();
            //크롤링하여 게임 스크린샷 업데이트
            gmVideoUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start(){
        gameVideoBatch();
    }

}
