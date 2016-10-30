package com.tenXen.core.model;

import com.tenXen.core.domain.Message;

/**
 * Created by wt on 2016/10/30.
 */
public class MessageModel extends Message {

    private int handlerCode;

    private String msg;

    private int resultCode;

    public int getHandlerCode() {
        return handlerCode;
    }

    public void setHandlerCode(int handlerCode) {
        this.handlerCode = handlerCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}
