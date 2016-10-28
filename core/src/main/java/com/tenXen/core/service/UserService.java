package com.tenXen.core.service;


import com.tenXen.core.domain.User;

import java.util.List;

/**
 * Created by wt on 2016/9/5.
 */
public interface UserService {

    User getUserById(int userId);

    int save(User user);

    User login(User user);

    List<User> getUserByUserName(String userName);
}
