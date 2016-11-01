package com.tenXen.core.service;

import com.tenXen.core.domain.User;
import com.tenXen.core.model.UserModel;

/**
 * @author createUser
 * @ClassName: UserService
 * @date 10月31日 10:20
 * wt
 */
public interface UserService extends BaseService<User> {

    UserModel doRegister(UserModel model) throws Exception;

    UserModel doLogin(UserModel model) throws Exception;

    UserModel doLogout(UserModel model) throws Exception;

    UserModel getOnlineUserList() throws Exception;

}