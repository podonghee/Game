package com.podong.game.module.game.dao;

import com.podong.game.module.game.bean.GameVO;
import com.podong.game.module.schduling.bean.GameCompanyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface GameMapper {
    List<GameVO> getGameList(Object data);
    int getGameCntList(Object data);
    List<GameVO> getDetail(Object data);
}
