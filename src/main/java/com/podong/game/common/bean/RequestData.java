package com.podong.game.common.bean;

import java.io.Serializable;
import java.util.HashMap;

public class RequestData implements Serializable {
    private Header header = new Header();
    private Object body;

    public RequestData() {
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

    public Object getBody(String key) {
        return ((HashMap)this.body).get(key);
    }

    public Object getBody() {
        return this.body;
    }
}
