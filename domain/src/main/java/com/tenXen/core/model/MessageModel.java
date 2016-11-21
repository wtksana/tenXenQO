package com.tenXen.core.model;

import com.tenXen.core.domain.Message;

import java.util.List;

/**
 * Created by wt on 2016/10/30.
 */
public class MessageModel extends Message {

    private static final long serialVersionUID = 1L;

    private String userName;

    private String nickname;

    private String toUserName;

    private int handlerCode;

    private String msg;

    private int resultCode;

    private int isEmotion;

    private List<MessageModel> unreadMsg;

    public List<MessageModel> getUnreadMsg() {
        return unreadMsg;
    }

    public void setUnreadMsg(List<MessageModel> unreadMsg) {
        this.unreadMsg = unreadMsg;
    }

    public int getIsEmotion() {
        return isEmotion;
    }

    public void setIsEmotion(int isEmotion) {
        this.isEmotion = isEmotion;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

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
