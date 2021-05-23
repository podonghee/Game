package com.podong.game.module.schduling.dao;

import com.podong.game.module.schduling.bean.GameCompanyVO;
import com.podong.game.module.schduling.bean.GameDataVO;
import com.podong.game.module.schduling.bean.GameSshotVO;
import com.podong.game.module.schduling.bean.GameVideoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BatchMapper {
    //GAMD_ID 값을 가지고 페이징 크롤링 하기위해 제일 맥스값 가져와서 작업 하기위함.
    String getGameNumber();
    //gmcid 값을 가지고 페이징 크롤링 하기위해 제일 맥스값 가져와서 작업 하기위함.
    String getGameCompanyNumber();

    int updateGameData(Object data);
    List<GameDataVO> getGameTotalList();
    //크롤링을 한 게임 데이터를 저장하기 위함.
    int insertGameCrawling(Object data);

    //크롤링을 한 게입업체 데이터를 저장하기 위함.
    int insertGameCompany(Object data);
    List<GameCompanyVO>getCompanyTotalList();
    int updateGameCompany(Object data);
    String getGameId(String gameName);
    //크롤링하여 게임랭킹 넣기
    int insertGameRank(Object data);

    int insertGameRankInfo(Object data);

    int insertGameSshot(Object data);
    int insertTotalGameSshot(Object data);
    List<GameSshotVO> getSelectShotList();
    int updateGameSshot(Object data);
    int deleteGameSshot(Object data);


    int insertGameVideo(Object data);
    int insertTotalGameVideo(Object data);
    List<GameVideoVO> getSelectVideoList();
    int updateGameVideo(Object data);
    int deleteGameVideo(Object data);

    int insertSsohtInfo(Object data);

    int insertGameTarget(Object data);
}
