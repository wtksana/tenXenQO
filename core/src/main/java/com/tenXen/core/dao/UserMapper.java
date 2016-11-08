package com.tenXen.core.dao;

import com.tenXen.core.domain.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    int countByUserName(String userName);

    void setUserOnline(int id);

    void setUserOffline(int id);

    List<User> getOnlineUserList();

}
