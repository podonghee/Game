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

            GameRankVO rankVo = new GameRankVO();
            for(int i=1; i <= 2103;i++) {
                doc = Jsoup.connect(gameRankUrl + i).get();
                elCnt = doc.select("span[class=\"ranking-date\"]");
                gameRankList = doc.select("table[class=\"ranking-table\"]");
                String strYear = elCnt.text().substring(0,4);
                String oneWeek = elCnt.text().replace(strYear+".","");
                rankVo.setGameRankYear(strYear);
                rankVo.setGameRankOneweek(oneWeek);
                rankVo.setGameRankId(i);

                //batchMapper.insertGameRank(rankVo);
                for (Element data : gameRankList) {
                    for (Element list : data.select("tr > td")) {
                        System.out.println(list.getElementsByClass("rank red").text());
                        System.out.println(list.getElementsByClass("rankChange"));

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
