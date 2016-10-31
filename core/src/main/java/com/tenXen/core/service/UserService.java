package com.tenXen.core.service;

import com.tenXen.core.domain.User;

/**
 * @author createUser
 * @ClassName: UserService
 * @date 10月31日 10:20
 * wt
 */
public interface UserService extends BaseService<User> {

    User login(User user) throws Exception;
}