package com.podong.game.module.gamevideo.module.impl;

import com.podong.game.common.bean.PagingInfo;
import com.podong.game.module.gamevideo.dao.GameVideoMapper;
import com.podong.game.module.gamevideo.module.GameVideoModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Author : po dong hee
 * Date : 2021.05.13
 * Description : 게임 랭킹 페이지
 * */
@Component("gameVideoModuleImpl")
public class GameVideoModuleImpl implements GameVideoModule {

    @Autowired
    private GameVideoMapper gameVideoMapper;
    @Override
    public Object se001(Object param) {
        try {
            HashMap reqMap = (HashMap)param;
            HashMap<String, Object> resMap = new HashMap<String, Object>();
            int totalCnt =  gameVideoMapper.getGameVideoCnt(reqMap);
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
            resMap.put("list", gameVideoMapper.getGameVideoList(reqMap));

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
            int totalCnt =  gameVideoMapper.getDetailVideoCnt(reqMap);
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
            resMap.put("list", gameVideoMapper.getDetailVideoList(reqMap));
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
            resMap.put("list", gameVideoMapper.getDetailToList(reqMap));
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
