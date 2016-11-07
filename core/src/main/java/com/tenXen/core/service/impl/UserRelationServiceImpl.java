package com.tenXen.core.service.impl;

import com.tenXen.common.util.StringUtil;
import com.tenXen.core.dao.UserMapper;
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
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getFriendsByUserId(int userId) {
        UserRelation userRelation = userRelationMapper.getRelationByUserId(userId);
        if (userRelation != null) {
            if (StringUtil.isNotBlank(userRelation.formatFriends())) {

            }
        }
        return null;
    }

    @Override
    public List<Group> getGroupsByUserId(int UserId) {
        return null;
    }
}
