package cn.zust.se.service.impl;

import cn.zust.se.dao.UserDao;
import cn.zust.se.eneity.User;
import cn.zust.se.service.UserService;

import javax.annotation.Resource;

public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;
    @Override
    public User login(String username) {
        return userDao.login(username);
    }
}
