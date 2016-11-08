package com.tenXen.core.model;

import com.tenXen.core.domain.UserFriend;

/**
 * Created by wt on 2016/11/8.
 */
public class UserFriendModel extends UserFriend {

    private static final long serialVersionUID = 1L;

    private String friend_userName;

    private String friend_nickname;

    private String friend_signature;

    private String friend_picPath;

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

    public String getFriend_picPath() {
        return friend_picPath;
    }

    public void setFriend_picPath(String friend_picPath) {
        this.friend_picPath = friend_picPath;
    }
}
