package com.podong.game.module.schduling.batched;

import com.podong.game.module.schduling.dao.BatchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("gameRankBatchClass")
public class GameRankBatchClass extends BasicClass {
    @Value("${repository.uploadGameImg}")
    private String uploadGameUrl;
    @Value("${crawling.gameRankUrl}")
    private String gameRankUrl;
    @Autowired
    private BatchMapper batchMapper;

    public void gameRankBatch(){

    }
    @Override
    public void start() {
        gameRankBatch();
    }

}
