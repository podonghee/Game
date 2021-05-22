package com.podong.game.service.impl;

import com.podong.game.common.bean.RequestData;
import com.podong.game.common.bean.ResponseData;
import com.podong.game.common.service.CommonService;
import com.podong.game.service.GameMekaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service("gameMekaServiceImpl")
public class GameMekaServiceImpl extends CommonService implements GameMekaService {

    enum ApplicationList{
        g001("gameModuleImpl")
        , g002("gameCompanyModuleImpl")
        , g004("gameSshotModuleImpl")
        , g005("gameVideoModuleImpl")
        ,g006("gameRankModuleImpl")
        ;
        private String value;
        ApplicationList(String arg){
            this.value = arg;
        }
        public String getValue(){
            return value;
        }
        public String toString() {
            return this.value;
        }
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public ResponseData extraTask(RequestData requestData) {
        return extraTask(applicationContext,requestData, ApplicationList.class);
    }
}