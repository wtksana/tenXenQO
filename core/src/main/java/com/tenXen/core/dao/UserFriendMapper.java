package com.tenXen.core.dao;

import com.tenXen.core.domain.UserFriend;
import com.tenXen.core.model.UserFriendModel;

import java.util.List;

public interface UserFriendMapper extends BaseMapper<UserFriend> {

    List<UserFriendModel> getFriendsByUserId(int userId);

}
