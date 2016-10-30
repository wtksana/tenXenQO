package com.tenXen.core.service.impl;

import com.tenXen.core.dao.UserMapper;
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
    private UserMapper userMapper;

    @Override
    public User getUserById(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int save(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User login(User user) {
        UserExample userExample = new UserExample();
        userExample.or().andUserNameEqualTo(user.getUserName()).andPwdEqualTo(user.getPwd());
        List<User> list = userMapper.selectByExample(userExample);
        User u = null;
        if (list != null && list.size() > 0) {
            u = list.get(0);
        }
        return u;
    }

    @Override
    public List<User> getUserByUserName(String userName) {
        UserExample userExample = new UserExample();
        userExample.or().andUserNameEqualTo(userName);
        return userMapper.selectByExample(userExample);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.selectByExample(null);
    }
}
