package com.podong.game.module.game.module.impl;

import com.podong.game.common.bean.PagingInfo;
import com.podong.game.module.game.dao.GameMapper;
import com.podong.game.module.game.module.GameModule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Author : po dong hee
 * Date : 2021.05.11
 * Description : 게임 DB 페이지
 * */
@Component("gameModuleImpl")
public class GameModuleImpl implements GameModule {

    @Autowired
    private GameMapper gameMapper;

    @Override
    public Object se001(Object param) {
        try {
            HashMap reqMap = (HashMap)param;
            HashMap<String, Object> resMap = new HashMap<String, Object>();
            List platformList = (List)reqMap.get("platform");
            if(platformList.size() == 0)
            {
                reqMap.put("platform","");
            }
            int totalCnt = gameMapper.getGameCntList(reqMap);
            int currentPage;
            int pageScale =0;
            if(null != reqMap.get( "page" ))
            {
                currentPage = Integer.parseInt(((HashMap<String, Object>) reqMap.get("page")).get("currentPage").toString());
                pageScale = Integer.parseInt(((HashMap<String, Object>) reqMap.get("page")).get("pageSize").toString());
            }
            else
            {
                currentPage = 0;
            }
            PagingInfo pagingInfo = new PagingInfo(totalCnt, currentPage,pageScale);
            resMap.put("page", pagingInfo);
            pagingInfo.setPageBegin(pagingInfo.getPageBegin()-1);
            reqMap.put("page", pagingInfo);
            resMap.put("list", gameMapper.getGameList(reqMap));

            return resMap;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Object dt001(Object param) {
        HashMap<String,Object> reqMap = (HashMap)param;
        return gameMapper.getDetail(reqMap);
    }

    @Override
    public Object sa001(Object param) {
        return null;
    }
}
