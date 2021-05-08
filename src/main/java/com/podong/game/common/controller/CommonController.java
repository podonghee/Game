package com.podong.game.common.controller;

import com.podong.game.common.bean.Header;
import com.podong.game.common.bean.RequestData;
import com.podong.game.common.bean.ResponseData;
import com.podong.game.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@ControllerAdvice
public abstract class CommonController {

    public CommonController() {
    }

    public Object _command(RequestData request, CommonService service) {
        System.out.println("********************************");
        System.out.println("            HEADER              ");
        System.out.println("********************************");
        System.out.println(" ModuleCode :" + request.getHeader().getModuleCode());
        System.out.println(" TaskCode :" + request.getHeader().getTaskCode());
        System.out.println("********************************");
        System.out.println("             BODY               ");
        System.out.println(request.getBody().toString());
        System.out.println("********************************");
        Header header = request.getHeader();
        ResponseData response = new ResponseData(request);
        String taskCode = header.getTaskCode();
        Class clazz = service.getClass();
        Method method = null;
        String var8 = header.getTaskCode();
        byte var9 = -1;
        switch(var9) {
            default:
                taskCode = "extraTask";
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                try {
                    method = clazz.getMethod(taskCode, RequestData.class);
                } catch (NoSuchMethodException var12) {
                    header.setErrorCode(-800);
                    header.setErrorMsg("지정한 업무코드가 존재하지 않습니다.");
                    response.setHeader(header);
                    response = null;
                }

                try {
                    response = (ResponseData)method.invoke(service, request);
                } catch (IllegalAccessException var10) {
                    header.setErrorCode(-801);
                    header.setErrorMsg("지정한 업무코드에 대한 잘못된 접근입니다.");
                    response.setHeader(header);
                } catch (InvocationTargetException var11) {
                    header.setErrorCode(-802);
                    header.setErrorMsg("지정한 업무코드에 대한 잘못된 호출입니다.");
                    response.setHeader(header);
                }

                return response;
        }
    }

    public abstract Object command(RequestData var1);


}
