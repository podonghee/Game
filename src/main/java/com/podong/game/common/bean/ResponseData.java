package com.podong.game.common.bean;


import java.io.Serializable;

public class ResponseData implements Serializable {
    private Header header = new Header();
    private Object body;

    public ResponseData() {
    }
    public ResponseData(RequestData requestData) {
        this.header = requestData.getHeader();
    }

    public Header getHeader() {
        return this.header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Object getBody() {
        return this.body;
    }
}
