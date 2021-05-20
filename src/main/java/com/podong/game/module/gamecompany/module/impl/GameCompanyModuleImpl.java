package com.podong.game.module.gamecompany.module.impl;

import com.podong.game.common.bean.PagingInfo;
import com.podong.game.module.gamecompany.dao.GameCompanyMapper;
import com.podong.game.module.gamecompany.module.GameCompanyModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Author : po dong hee
 * Date : 2021.05.14
 * Description : 게임 업체 페이지
 * */
@Component("gameCompanyModuleImpl")
public class GameCompanyModuleImpl implements GameCompanyModule {

    @Autowired
    private GameCompanyMapper gameCompanyMapper;
    @Override
    public Object se001(Object param) {
        try {
            HashMap reqMap = (HashMap)param;
            HashMap<String, Object> resMap = new HashMap<String, Object>();
            int totalCnt = gameCompanyMapper.getGameCompanyCnt(reqMap);
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
            resMap.put("list", gameCompanyMapper.getGameCompanyList(reqMap));

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
        return gameCompanyMapper.getGameCompanyDetail(reqMap);
    }

    @Override
    public Object sa001(Object param) {
        return null;
    }
}
