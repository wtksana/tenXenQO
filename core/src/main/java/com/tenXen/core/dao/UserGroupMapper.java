package com.tenXen.core.dao;

import com.tenXen.core.domain.UserGroup;
import com.tenXen.core.model.UserGroupModel;

import java.util.List;

public interface UserGroupMapper extends BaseMapper<UserGroup> {

    List<UserGroupModel> getGroupsByUserId(int userId);
}
