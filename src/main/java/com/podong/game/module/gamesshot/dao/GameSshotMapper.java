package com.podong.game.module.gamesshot.dao;

import com.podong.game.module.gamerank.bean.GameRankInfoVO;
import com.podong.game.module.gamesshot.bean.GameSshotInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface GameSshotMapper {
    List<HashMap<String,Object>> getGameSshotList(Object data);
    int getGameSshotCnt(Object data);

    List<HashMap<String,Object>> getDetailSshotList(Object data);
    int getDetailSshotCnt(Object data);


    List<HashMap<String,Object>> getDetailToList(Object data);

}
