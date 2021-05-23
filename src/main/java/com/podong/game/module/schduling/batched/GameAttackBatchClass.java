package com.podong.game.module.schduling.batched;

import com.podong.game.module.schduling.bean.GameVideoVO;
import com.podong.game.module.schduling.dao.BatchMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("gameAttackBatchClass")
public class GameAttackBatchClass extends BasicClass {
    @Value("${crawling.gameAttackUrl}")
    private String gameAttackUrl;
    @Autowired
    private BatchMapper batchMapper;

    public void gameVideoBatch() {
        boolean flag = true;
        try {
            Document doc = null;
            Elements elCnt = null;
            Elements elm = null;
            GameVideoVO vo = new GameVideoVO();
            String aTag = null;
            String gmId = null;
            while (flag) {
                for (int i = 1; i < 10; i++) {
                    doc = Jsoup.connect(gameAttackUrl + i).get();
                    elm = doc.select("div[class=\"game-thumb-box \"]");
                    for (Element data : elm) {
                        for (Element list : data.select("ul > li")) {
                            aTag = list.select("a").attr("href");
                            vo.setGameVideoUrl(aTag);
                            gmId = aTag.replace("?rts=gmview&gmid=", "");
                            gmId = gmId.replace("&tab=movie", "");
                            vo.setGameId(gmId);
                            batchMapper.insertGameVideo(vo);
                        }
                    }
                }
                flag = false;
            }
            String description = "";
            String imgUrl = "";
            List<GameVideoVO> gameVideoList = batchMapper.getSelectVideoList();
            int i = 1;
            for(GameVideoVO vevo : gameVideoList){
                i++;
                doc = Jsoup.connect("https://www.gamemeca.com/game.php"+vevo.getGameVideoUrl()).get();
                elm = doc.select("div[class=\"db_thumlist \"]");
                for (Element data : elm) {

                    for (Element list : data.select("ul > li")) {
                        imgUrl = list.select("img").attr("src");
                        description = list.text();
                        vevo.setGameVideoImgUrl(imgUrl);
                        vevo.setDescription(description);
                        if(data.select("ul > li").size() > 1){
                            batchMapper.deleteGameVideo(vevo);
                            batchMapper.insertTotalGameVideo(vevo);
                        }
                        else {

                            batchMapper.updateGameVideo(vevo);
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
        gameVideoBatch();
    }

}
