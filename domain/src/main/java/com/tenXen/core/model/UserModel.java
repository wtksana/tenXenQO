package com.tenXen.core.model;

import com.tenXen.core.domain.User;

/**
 * Created by wt on 2016/10/26.
 */
public class UserModel extends User {

    public static int LOGIN_CODE = 1;

    public static int REGISTER_CODE = 2;

    private int handlerCode;

    public int getHandlerCode() {
        return handlerCode;
    }

    public void setHandlerCode(int handlerCode) {
        this.handlerCode = handlerCode;
    }
}
