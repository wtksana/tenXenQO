package com.tenXen.core.service.impl;

import com.tenXen.core.dao.UserGroupMapper;
import com.tenXen.core.domain.UserGroup;
import com.tenXen.core.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class UserGroupServiceImpl extends BaseServiceImpl<UserGroup> implements UserGroupService {

    @Autowired
    private UserGroupMapper userGroupMapper;


}
