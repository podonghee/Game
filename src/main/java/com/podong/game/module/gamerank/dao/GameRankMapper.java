package com.podong.game.module.gamerank.dao;

import com.podong.game.module.gamerank.bean.GameRankInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GameRankMapper {
    List<GameRankInfoVO> getRankList(Object data);
}
