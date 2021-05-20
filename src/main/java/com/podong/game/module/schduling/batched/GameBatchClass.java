package com.podong.game.module.schduling.batched;

import com.podong.game.module.schduling.bean.GameDataVO;
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

@Component("gameBatchClass")
public class GameBatchClass extends BasicClass {
    @Value("${repository.uploadGameImg}")
    private String uploadGameUrl;
    @Value("${crawling.gameUrl}")
    private String gameUrl;
    @Autowired
    private BatchMapper batchMapper;

    public void gameBatch(){
        //크롬드라이브 접속
        WebDriver driver = chromeDriver(gameUrl);
        boolean flag = true;
        //총 토탈 게임 갯수
        WebElement elCnt = driver.findElement(By.id("dblist-total"));
        // dblist-total element 값이 8,306개의 게임 정보가 있습니다. < 이형식 나중에 만개이상일때까지 생각하여 구성
        String strCnt = elCnt.getText().substring(0,6).replace(",","");
        if(strCnt.substring(4,5).equals("개"))
        {
            //strCnt 가 1000개 일수도 10000개 일수도 있음. 만약 개라는 텍스트가 마지막에 있으면 replace로 삭제
            // 아닌경우는 바로 스킵
            strCnt = strCnt.replace("개","");
        }
        //형변환
        int totalCnt = Integer.parseInt(strCnt);

        int nextCnt = totalCnt;
        GameDataVO vo = new GameDataVO();
        while (flag) {
            //한페이지마다 클릭시 25개의 리스트가 나옴. 총토탈카운트에서 -25개만큼 계속 뺴줌.
            nextCnt =  nextCnt -= 25;
            List<WebElement> webElements = driver.findElements(By.cssSelector("ul.dblist_gallery > li > a"));
            List<WebElement> img = driver.findElements(By.cssSelector("ul.dblist_gallery > li > a > img"));
            System.out.println("******  이관시작 GM_GAME 데이터 Insert !!  ******");
            int i = -1;
            for(WebElement wEl : webElements) {
                i++;
                //게임ID 셋팅
                vo.setGameId(wEl.getAttribute("data-gmid"));
                //게임명 셋팅
                vo.setGameName(wEl.getText());
                //게임이미지 셋팅
                vo.setImg(img.get(i).getAttribute("src"));
                //이미지다운로드
                //imgDownload(vo.getImg(), vo.getGameName(), uploadGameUrl);
                // GM_GAME Insert
                batchMapper.insertGameCrawling(vo);
                System.out.println("******  성공한 데이터 GAME_ID : "+ vo.getGameId() + " GAME_NAME : "+ vo.getGameName()+"  ******");
            }
            //nextCnt 가 0보다 작은경우는 이미 더이상 페이지를 클릭할 수 없으므로 flag 를 false로 줘서 while 반복문 빠져나감.
            if(nextCnt < 0)
            {
                flag = false;
                System.out.println(" *******  이관완료 전체 게임 수 : "+totalCnt+
                        " 전체 총 페이지 수 :"  + (totalCnt/25) +" ******* ");
            }
            else
            {
                WebElement el13 = driver.findElement(By.className("num_next"));
                System.out.println("현재 페이지 수 :"+ el13.getAttribute("data-p"));
                el13.click();
                try { Thread.sleep(1000);} catch (InterruptedException e) {}
            }
        }
        try {
            System.out.println("GAME LIST 가져오기");
            Document doc = null;
            Elements elm = null;
            Elements descEl = null;
            List<GameDataVO> gameList = batchMapper.getGameTotalList();
            for (GameDataVO gm : gameList) {
                //크롤링할 페이지 URL
                vo.setGameId(gm.getGameId());
                vo.setGameName(gm.getGameName());
                doc = Jsoup.connect(gameUrl + "?rts=gmview&gmid=" + gm.getGameId()).get();
                //크롤링 페이지 정보들 엘리먼트로 파싱 게임명 등 상세정보가 들어있음.
                elm = doc.select("div[class=\"gm_info\"]");
                for (Element data : elm) {
                    for (Element list : data.select("ul")) {
                        switchGame(list.getElementsByClass("leftA").text(), list.getElementsByClass("rightA").text(), vo);
                    }
                }
                descEl = doc.select("div[class=\"db-cont1-left\"]");
                switchGame("게임설명", descEl.text(), gm);
                batchMapper.updateGameData(vo);
                System.out.println("성공한 게임 :" + gm.getGameName() + " 게임번호 : " + gm.getGameId());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void start(){
        gameBatch();
    }

}
