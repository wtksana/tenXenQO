package com.tenXen.core.model;

import com.tenXen.core.domain.User;

import java.util.List;

/**
 * Created by wt on 2016/10/26.
 */
public class UserModel extends User {

    private static final long serialVersionUID = 1L;

    private int handlerCode;

    private String msg;

    private int resultCode;

    private List userList;

    private User self;

    public User getSelf() {
        return self;
    }

    public void setSelf(User self) {
        this.self = self;
    }

    public List getUserList() {
        return userList;
    }

    public void setUserList(List userList) {
        this.userList = userList;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

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
