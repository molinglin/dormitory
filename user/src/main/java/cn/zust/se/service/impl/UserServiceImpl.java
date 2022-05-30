package cn.zust.se.service.impl;

import cn.zust.se.dao.UserDao;
import cn.zust.se.eneity.User;
import cn.zust.se.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;
    @Override
    public User login(String username) {
        return userDao.login(username);
    }

    @Override
    public Integer updateMaster(String did, String phone, Integer building_id) {
        return userDao.updateMaster(did, phone, building_id);
    }

    @Override
    public Integer updateStu(String uid, String phone, String college, String major, String classes) {
        return userDao.updateStu(uid, phone, college, major, classes);
    }

    @Override
    public Integer updateUserPw(String username, String password) {
        return userDao.updateUserPw(username,password);
    }
}
