package com.tenXen.core.model;

import com.tenXen.core.domain.User;

/**
 * Created by wt on 2016/10/26.
 */
public class UserModel extends User {

    private int handlerCode;

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getHandlerCode() {
        return handlerCode;
    }

    public void setHandlerCode(int handlerCode) {
        this.handlerCode = handlerCode;
    }
}
