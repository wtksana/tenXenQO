package com.tenXen.core.service.impl;

import com.tenXen.core.dao.UserDao;
import com.tenXen.core.domain.User;
import com.tenXen.core.domain.example.UserExample;
import com.tenXen.core.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wt on 2016/9/5.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User getUserById(int userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public int save(User user) {
        return userDao.insert(user);
    }

    @Override
    public List<User> getUserByUserName(String userName) {
        UserExample userExample = new UserExample();
        userExample.or().andUserNameEqualTo(userName);
        return userDao.selectByExample(userExample);
    }
}
