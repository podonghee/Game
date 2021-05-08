package com.podong.game.common.bean;

import java.io.Serializable;

public class Header implements Serializable {
    private boolean result = true;
    private String moduleCode = "";
    private String taskCode = "";
    private int errorCode = -1;
    private String errorMsg = "";

    public Header() {
    }

    public boolean getResult() {
        return this.result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getModuleCode() {
        return this.moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getTaskCode() {
        return this.taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
