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
            for(int i=1; i <= 2103;i++) {
                System.out.println("gameRankUrl : " + i+"");
                doc = Jsoup.connect(gameRankUrl + i).get();
                elCnt = doc.select("span[class=\"ranking-date\"]");
                gameRankList = doc.select("table[class=\"ranking-table\"]");
                String strYear = elCnt.text().substring(0,4);
                String oneWeek = elCnt.text().replace(strYear+".","");
                System.out.println("strYear : " +strYear);
                System.out.println("oneWeek : " +oneWeek);
                System.out.println("RankId : " +i);

                rankVo.setGameRankYear(strYear);
                rankVo.setGameRankOneWeek(oneWeek);
                rankVo.setGameRankId(i);


                batchMapper.insertGameRank(rankVo);
                for (Element data : gameRankList) {
                    for (Element list : data.select("tr[class=\"ranking-table-rows\"]")) {
                        System.out.println("gameName :" + list.getElementsByClass("game-name").text());
                        gameId = batchMapper.getGameId(list.getElementsByClass("game-name").text());
                        if(gameId == null) {
                            break;
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
                            System.out.println("gameRankPast : " + gameRankPast);
                            iconStr = "up";
                            System.out.println("iconStr : " + iconStr);
                        }
                        else if(iconStr.contains("down")){
                            gameRankPast = Integer.parseInt(list.getElementsByClass("rankChange").text());
                            System.out.println("gameRankPast : " + gameRankPast);
                            iconStr = "down";
                            System.out.println("iconStr : " + iconStr);
                        }
                        else{
                            iconStr = "";
                        }
                        rankVo.setGameRankStatus(iconStr);
                        rankVo.setGameRankPast(gameRankPast);
                        batchMapper.insertGameRankInfo(rankVo);

                    }
                }
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
