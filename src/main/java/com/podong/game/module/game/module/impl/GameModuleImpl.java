package com.podong.game.module.game.module.impl;

import com.podong.game.common.bean.PagingInfo;
import com.podong.game.module.game.dao.GameMapper;
import com.podong.game.module.game.module.GameModule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Component("gameModuleImpl")
public class GameModuleImpl implements GameModule {

    @Autowired
    private GameMapper gameMapper;

    public Object gameSelectList(Object data) {
        try {
            HashMap reqMap = (HashMap)data;
            HashMap<String, Object> resMap = new HashMap<String, Object>();
            List platformList = (List)reqMap.get("platform");
            if(platformList.size() == 0){
                reqMap.put("platform","");
            }
            int totalCnt = gameMapper.getGameCntList(reqMap);
            int currentPage;
            if(null != reqMap.get( "page" )) {
                currentPage = Integer.parseInt(((HashMap<String, Object>) reqMap.get("page")).get("currentPage").toString());
            } else {
                currentPage = 0;
            }

            PagingInfo pagingInfo = new PagingInfo(totalCnt, currentPage);
            reqMap.put("page", pagingInfo);
            resMap.put("list", gameMapper.getGameList(reqMap));
            resMap.put("page", pagingInfo);
            return resMap;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
