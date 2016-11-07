package com.tenXen.core.service.impl;

import com.tenXen.core.dao.UserRelationMapper;
import com.tenXen.core.domain.Group;
import com.tenXen.core.domain.User;
import com.tenXen.core.domain.UserRelation;
import com.tenXen.core.service.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class UserRelationServiceImpl extends BaseServiceImpl<UserRelation> implements UserRelationService {

    @Autowired
    private UserRelationMapper userRelationMapper;

    @Override
    public List<User> getFriendsByUserId(int userId) {
        return null;
    }

    @Override
    public List<Group> getGroupsByUserId(int UserId) {
        return null;
    }
}
