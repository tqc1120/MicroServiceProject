package com.example.common.domain;

import java.util.Date;

public class GeneralResponse {
    private int code;
    private Date timestamp;
    private Object data;

    public GeneralResponse() {
    }

    public GeneralResponse(int code, Date timestamp, Object data) {
        this.code = code;
        this.timestamp = timestamp;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
