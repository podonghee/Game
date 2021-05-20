package com.podong.game.module.schduling.batched;

import com.podong.game.module.schduling.bean.GameDataVO;
import com.podong.game.module.schduling.bean.GameSshotVO;
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

@Component("gameSshotBatchClass")
public class GameSshotClass extends BasicClass {
    @Value("${crawling.gameSshotUrl}")
    private String gameSshotUrl;
    @Autowired
    private BatchMapper batchMapper;

    public void gameSshotBatch() {
        boolean flag = true;
        try {
            Document doc = null;
            Elements elCnt = null;
            Elements elm = null;
            GameSshotVO vo = new GameSshotVO();
            String aTag = null;
            String gmId = null;
            while (flag) {
                for (int i = 1; i < 146; i++) {
                    doc = Jsoup.connect(gameSshotUrl + i).get();
                    elm = doc.select("div[class=\"game-thumb-box \"]");
                    for (Element data : elm) {
                        for (Element list : data.select("ul > li")) {
                            aTag = list.select("a").attr("href");
                            vo.setGameSshotUrl(aTag);
                            gmId = aTag.replace("?rts=gmview&gmid=", "");
                            gmId = gmId.replace("&tab=sshot", "");
                            vo.setGameId(gmId);
                            batchMapper.insertGameSshot(vo);
                        }
                    }
                }
                flag = false;
            }
            String description = "";
            String imgUrl = "";
            List<GameSshotVO> gameShotList = batchMapper.getSelectShotList();
            int i = 1;
            for(GameSshotVO shvo : gameShotList){
                i++;
                doc = Jsoup.connect("https://www.gamemeca.com/game.php"+shvo.getGameSshotUrl()).get();
                elm = doc.select("div[class=\"db_thumlist \"]");
                for (Element data : elm) {

                    for (Element list : data.select("ul > li")) {
                        imgUrl = list.select("img").attr("src");
                        description = list.text();
                        shvo.setGameSshotImgUrl(imgUrl);
                        shvo.setDescription(description);
                        if(data.select("ul > li").size() > 1){
                            batchMapper.deleteGameSshot(shvo);
                            batchMapper.insertTotalGameSshot(shvo);
                        }
                        else {

                            batchMapper.updateGameSshot(shvo);
                        }
                    }
                }
                System.out.println(i);
            }
        System.out.println("끝");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start(){
        gameSshotBatch();
    }

}
