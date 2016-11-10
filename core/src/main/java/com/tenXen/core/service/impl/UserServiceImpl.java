package com.tenXen.core.service.impl;

import com.tenXen.common.constant.Constants;
import com.tenXen.core.dao.UserFriendMapper;
import com.tenXen.core.dao.UserGroupMapper;
import com.tenXen.core.dao.UserMapper;
import com.tenXen.core.domain.User;
import com.tenXen.core.model.UserFriendModel;
import com.tenXen.core.model.UserGroupModel;
import com.tenXen.core.model.UserModel;
import com.tenXen.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserFriendMapper userFriendMapper;
    @Autowired
    private UserGroupMapper userGroupMapper;

    @Override
    public UserModel doRegister(UserModel model) throws Exception {
        try {
            User u = new User(model.getUserName(), model.getPwd());
            int count = userMapper.countByUserName(model.getUserName());
            if (count > 0) {
                model.setMsg("用户名已被注册");
                model.setResultCode(Constants.RESULT_FAIL);
                return model;
            }
            User user = this.saveModel(u);
            if (user != null) {
                model.setMsg("注册成功" + u.getUserName());
                model.setResultCode(Constants.RESULT_SUC);
                return model;
            } else {
                model.setMsg("注册失败，请输入正常的用户名密码");
                model.setResultCode(Constants.RESULT_FAIL);
                return model;
            }
        } catch (Exception e) {
            model.setMsg(e.getMessage());
            model.setResultCode(Constants.RESULT_FAIL);
        }
        return model;
    }

    @Override
    public UserModel doLogin(UserModel model) throws Exception {
        try {
            User u = new User(model.getUserName(), model.getPwd());
            List<User> list = userMapper.findModelList(u);
            if (list != null && list.size() > 0) {
                u = list.get(0);
                userMapper.setUserOnline(u.getId());
                List<UserFriendModel> friends = userFriendMapper.getFriendsByUserId(u.getId());
                if (friends != null && friends.size() > 0) {
                    model.setFriends(friends);
                }
                List<UserGroupModel> groups = userGroupMapper.getGroupsByUserId(u.getId());
                if (groups != null && groups.size() > 0) {
                    model.setGroups(groups);
                }
                model.setSelf(u);
                model.setMsg("登录成功" + u.getUserName());
                model.setResultCode(Constants.RESULT_SUC);
            } else {
                model.setMsg("登录失败，用户名或密码错误");
                model.setResultCode(Constants.RESULT_FAIL);
            }
        } catch (Exception e) {
            model.setMsg(e.getMessage());
            model.setResultCode(Constants.RESULT_FAIL);
        }
        return model;
    }

    @Override
    public UserModel doLogout(UserModel model) throws Exception {
        User u = model.getSelf();
        if (u != null) {
            userMapper.setUserOffline(u.getId());
            model.setResultCode(Constants.RESULT_SUC);
            model.setMsg("登出成功" + u.getUserName());
        } else {
            model.setResultCode(Constants.RESULT_FAIL);
            model.setMsg("登出失败" + u.getUserName());
        }
        return model;
    }

    @Override
    public UserModel getOnlineUserList() throws Exception {
        UserModel model = new UserModel();
        List<User> list = userMapper.getOnlineUserList();
        if (list != null && list.size() > 0) {
            model.setHandlerCode(Constants.UPDATE_ONLINE_USER);
            model.setResultCode(Constants.RESULT_SUC);
            model.setUserList(list);
        } else {
            model.setResultCode(Constants.RESULT_FAIL);
        }
        return model;
    }

}
