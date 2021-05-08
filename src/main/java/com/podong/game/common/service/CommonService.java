package com.podong.game.common.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.podong.game.common.bean.Header;
import com.podong.game.common.bean.RequestData;
import com.podong.game.common.bean.ResponseData;
import com.podong.game.common.module.CommonModule;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class CommonService {

    public CommonService() {
    }
    public <E extends Enum<E>> CommonModule getModule(ApplicationContext context, String moduleCode, Class<E> enumClass)  {
        CommonModule commonModule = null;
        Enum[] applicationList = (Enum[])enumClass.getEnumConstants();
        Object[] list = Arrays.stream(applicationList).filter((vo) -> {
            return vo.name().equals(moduleCode);
        }).toArray();
        if (list.length < 1) {
        } else {
            commonModule = (CommonModule)context.getBean(list[0].toString());
            if (null == commonModule) {
            } else {
                return commonModule;
            }
        }
        return null;
    }

    public abstract ResponseData extraTask(RequestData var1);

    public <E extends Enum<E>> ResponseData extraTask(ApplicationContext context, RequestData request, Class<E> enumClass) {
        return this.executeModule(context, request, enumClass);
    }

    private <E extends Enum<E>> ResponseData executeModule(ApplicationContext context, RequestData request, Class<E> enumClass) {
        ResponseData response = new ResponseData(request);
        Header header = new Header();
        response.setHeader(header);
        CommonModule cm = null;

        try {
            cm = this.getModule(context, request.getHeader().getModuleCode(), enumClass);
        } catch (Exception var17) {
            response.getHeader().setResult(false);
            response.getHeader().setErrorMsg(var17.getMessage());
        }

        String errorMsgCode;
        HashMap reqData;
        ResponseData responseData;
        try {
            HashMap<String, Object> retData = new HashMap();
            HashMap<String, Object> data = (HashMap)request.getBody();
            errorMsgCode = request.getHeader().getTaskCode();
            reqData = null;
            Method method;
            method = cm.getClass().getMethod(errorMsgCode, Object.class);

            ObjectMapper objectMapper = new ObjectMapper();
            responseData = null;
            Object param;
            if (data.containsKey("contents")) {
                param = data.get("contents");
            } else if (data.containsKey("list")) {
                param = data.get("list");
            } else {
                param = data;
            }

            Object result = method.invoke(cm, param);
            if (result instanceof List) {
                retData.put("list", result);
                response.setBody(retData);
            } else {
                retData.put("contents", result);
                response.setBody(retData);
            }
        } catch (Exception var18) {

        }

        return response;
    }
}