package com.tenXen.core.service.impl;

import com.tenXen.core.dao.GroupMapper;
import com.tenXen.core.domain.Group;
import com.tenXen.core.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class GroupServiceImpl extends BaseServiceImpl<Group> implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

}
