package com.tenXen.core.dao;

import com.tenXen.core.domain.UserRelation;

public interface UserRelationMapper extends BaseMapper<UserRelation> {

    UserRelation getRelationByUserId(int userId);

}
