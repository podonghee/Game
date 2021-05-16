package com.podong.game.module.schduling.batched;

import com.podong.game.module.schduling.bean.GameRankVO;
import com.podong.game.module.schduling.dao.BatchMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component("gameRankBatchClass")
public class GameRankBatchClass extends BasicClass {
    @Value("${repository.uploadRankImg}")
    private String uploadRankUrl;
    @Value("${crawling.gameRankUrl}")
    private String gameRankUrl;
    @Autowired
    private BatchMapper batchMapper;

    public void gameRankBatch(){
        try{
            Document doc = null;
            Elements elCnt = null;
            Elements gameRankList = null;
            String iconStr = null;
            int gameRankPast = 0;
            GameRankVO rankVo = new GameRankVO();
            String gameId = null;
            int count = 0;
            for(int i=1; i <= 2103;i++) {
                System.out.println("gameRankUrl : " + i+"");
                doc = Jsoup.connect(gameRankUrl + i).get();
                elCnt = doc.select("span[class=\"ranking-date\"]");
                gameRankList = doc.select("table[class=\"ranking-table\"]");
                String strYear = elCnt.text().substring(0,4);
                String oneWeek = elCnt.text().replace(strYear+".","");

                rankVo.setGameRankYear(strYear);
                rankVo.setGameRankOneWeek(oneWeek);
                rankVo.setGameRankId(i);
                rankVo.setGameLevel(i);

                batchMapper.insertGameRank(rankVo);

                for (Element data : gameRankList) {
                    for (Element list : data.select("tr[class=\"ranking-table-rows\"]")) {
                        count++;
                        try{
                            gameId = batchMapper.getGameId(list.getElementsByClass("game-name").text());
                        }catch (Exception e){
                            System.out.println(gameId);
                            System.out.println(list.getElementsByClass("game-name").text());
                            e.printStackTrace();
                        }
                        if(gameId == null) {
                            rankVo.setGameId("001");
                        }else{
                            rankVo.setGameId(gameId);
                        }
                        rankVo.setGameRankCount(Integer.parseInt(list.getElementsByClass("rank").text()));
                        rankVo.setGameRankImg(list.getElementsByClass("game-icon").attr("src"));
                        iconStr = list.getElementsByClass("rankChange").html();
                        list.getElementsByClass("rankChange");
                        if(iconStr.contains("new")) {
                            iconStr = "new";
                        }
                        else if(iconStr.contains("up")){
                            gameRankPast = Integer.parseInt(list.getElementsByClass("rankChange").text());
                            iconStr = "up";
                        }
                        else if(iconStr.contains("down")){
                            gameRankPast = Integer.parseInt(list.getElementsByClass("rankChange").text());
                            iconStr = "down";
                        }
                        else{
                            iconStr = "";
                        }
                        rankVo.setGameRankStatus(iconStr);
                        rankVo.setGameRankPast(gameRankPast);
                        batchMapper.insertGameRankInfo(rankVo);

                    }
                }

                System.out.println("strYear : "+ strYear + "oneWeek : "+
                        oneWeek +" 총카운트 : " +count);
                count = 0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public void start() {
        gameRankBatch();
    }

}
