package com.tenXen.core.service.impl;

import com.tenXen.core.dao.UserMapper;
import com.tenXen.core.domain.User;
import com.tenXen.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author createUser
 * @ClassName: UserServiceImpl
 * @date 10月31日 10:20
 * wt
 */
@Service()
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) throws Exception {
        List<User> list = userMapper.findModelList(user);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
