package com.tenXen.core.service;

import com.tenXen.core.domain.Group;
import com.tenXen.core.domain.User;
import com.tenXen.core.domain.UserRelation;

import java.util.List;

public interface UserRelationService extends BaseService<UserRelation> {

    List<User> getFriendsByUserId(int userId);

    List<Group> getGroupsByUserId(int UserId);

}
