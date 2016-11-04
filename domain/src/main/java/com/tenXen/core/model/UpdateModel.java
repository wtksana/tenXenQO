package com.tenXen.core.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wt on 2016/11/4.
 */
public class UpdateModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int updateCode;
    private String Msg;
    private int resultCode;
    private Object updateFile;
    private List<String> userEmotionList;
    private List<String> serverEmotionList;
    private List<byte[]> emotionDatas;

    public List<String> getUserEmotionList() {
        return userEmotionList;
    }

    public void setUserEmotionList(List<String> userEmotionList) {
        this.userEmotionList = userEmotionList;
    }

    public List<String> getServerEmotionList() {
        return serverEmotionList;
    }

    public void setServerEmotionList(List<String> serverEmotionList) {
        this.serverEmotionList = serverEmotionList;
    }

    public List<byte[]> getEmotionDatas() {
        return emotionDatas;
    }

    public void setEmotionDatas(List<byte[]> emotionDatas) {
        this.emotionDatas = emotionDatas;
    }

    public int getUpdateCode() {
        return updateCode;
    }

    public void setUpdateCode(int updateCode) {
        this.updateCode = updateCode;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Object getUpdateFile() {
        return updateFile;
    }

    public void setUpdateFile(Object updateFile) {
        this.updateFile = updateFile;
    }
}
