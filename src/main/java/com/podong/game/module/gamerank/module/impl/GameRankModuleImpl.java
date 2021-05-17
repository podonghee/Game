package com.podong.game.module.gamerank.module.impl;

import com.podong.game.module.gamerank.dao.GameRankMapper;
import com.podong.game.module.gamerank.module.GameRankModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * Author : po dong hee
 * Date : 2021.05.13
 * Description : 게임 랭킹 페이지
 * */
@Component("gameRankModuleImpl")
public class GameRankModuleImpl implements GameRankModule {

    @Autowired
    private GameRankMapper gameRankMapper;
    @Override
    public Object se001(Object param) {
        return gameRankMapper.getRankList(param);
    }

    @Override
    public Object dt001(Object param) {
        return null;
    }

    @Override
    public Object sa001(Object param) {
        return null;
    }
}
