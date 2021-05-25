package com.podong.game.module.gamesshot.module.module.impl;

import com.podong.game.common.bean.PagingInfo;
import com.podong.game.module.gamerank.dao.GameRankMapper;
import com.podong.game.module.gamerank.module.GameRankModule;
import com.podong.game.module.gamesshot.dao.GameSshotMapper;
import com.podong.game.module.gamesshot.module.module.GameSshotModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Author : po dong hee
 * Date : 2021.05.13
 * Description : 게임 스크린샷 페이지
 * */
@Component("gameSshotModuleImpl")
public class GameSshotModuleImpl implements GameSshotModule {

    @Autowired
    private GameSshotMapper gameSshotMapper;
    @Override
    public Object se001(Object param) {
        try {
            HashMap reqMap = (HashMap)param;
            HashMap<String, Object> resMap = new HashMap<String, Object>();
            int totalCnt =  gameSshotMapper.getGameSshotCnt(reqMap);
            int currentPage;
            int pageScale = 0;
            if(null != reqMap.get( "page" )) {
                currentPage = Integer.parseInt(((HashMap<String, Object>) reqMap.get("page")).get("currentPage").toString());
                pageScale = Integer.parseInt(((HashMap<String, Object>) reqMap.get("page")).get("pageSize").toString());

            } else {
                currentPage = 0;
            }

            PagingInfo pagingInfo = new PagingInfo(totalCnt, currentPage,pageScale);
            resMap.put("page", pagingInfo);
            pagingInfo.setPageBegin(pagingInfo.getPageBegin()-1);
            reqMap.put("page", pagingInfo);
            resMap.put("list", gameSshotMapper.getGameSshotList(reqMap));

            return resMap;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object dt001(Object param) {
        try {
            HashMap reqMap = (HashMap)param;
            HashMap<String, Object> resMap = new HashMap<String, Object>();
            int totalCnt =  gameSshotMapper.getDetailSshotCnt(reqMap);
            int currentPage;
            int pageScale = 0;
            if(null != reqMap.get( "page" )) {
                currentPage = Integer.parseInt(((HashMap<String, Object>) reqMap.get("page")).get("currentPage").toString());
                pageScale = Integer.parseInt(((HashMap<String, Object>) reqMap.get("page")).get("pageSize").toString());

            } else {
                currentPage = 0;
            }
            PagingInfo pagingInfo = new PagingInfo(totalCnt, currentPage,pageScale);
            resMap.put("page", pagingInfo);
            pagingInfo.setPageBegin(pagingInfo.getPageBegin()-1);
            reqMap.put("page", pagingInfo);
            resMap.put("list", gameSshotMapper.getDetailSshotList(reqMap));
            return resMap;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Object dt002(Object param) {
        try {
            HashMap reqMap = (HashMap)param;
            HashMap<String, Object> resMap = new HashMap<String, Object>();
            resMap.put("list", gameSshotMapper.getDetailToList(reqMap));
            return resMap;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object sa001(Object param) {
        return null;
    }
}
