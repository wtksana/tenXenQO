package com.tenXen.core.service.impl;

import com.tenXen.core.dao.UserFriendMapper;
import com.tenXen.core.domain.UserFriend;
import com.tenXen.core.service.UserFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class UserFriendServiceImpl extends BaseServiceImpl<UserFriend> implements UserFriendService {

    @Autowired
    private UserFriendMapper userFriendMapper;

}
