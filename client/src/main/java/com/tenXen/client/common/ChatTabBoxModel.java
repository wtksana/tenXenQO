package com.tenXen.client.common;

import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

/**
 * Created by wt on 2016/11/10.
 */
public class ChatTabBoxModel {

    private Tab tab;

    private VBox vBox;

    private String friend_userName;

    private String friend_nickname;

    private String friend_signature;

    private int friend_id;

    public ChatTabBoxModel(Tab tab, VBox vBox, String friend_userName, String friend_nickname) {
        this.tab = tab;
        this.vBox = vBox;
        this.friend_userName = friend_userName;
        this.friend_nickname = friend_nickname;
    }

    public Tab getTab() {
        return tab;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public String getFriend_userName() {
        return friend_userName;
    }

    public void setFriend_userName(String friend_userName) {
        this.friend_userName = friend_userName;
    }

    public String getFriend_nickname() {
        return friend_nickname;
    }

    public void setFriend_nickname(String friend_nickname) {
        this.friend_nickname = friend_nickname;
    }

    public String getFriend_signature() {
        return friend_signature;
    }

    public void setFriend_signature(String friend_signature) {
        this.friend_signature = friend_signature;
    }

    public int getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(int friend_id) {
        this.friend_id = friend_id;
    }
}
