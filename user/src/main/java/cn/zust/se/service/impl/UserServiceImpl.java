package cn.zust.se.service.impl;

import cn.zust.se.dao.UserDao;
import cn.zust.se.eneity.Master;
import cn.zust.se.eneity.Stu;
import cn.zust.se.eneity.User;
import cn.zust.se.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;
    @Override
    public User login(String username) {
        return userDao.login(username);
    }

    @Override
    public Stu selStu(String uid) {
        return userDao.selStu(uid);
    }

    @Override
    public Master selMaster(String did) {
        return userDao.selMaster(did);
    }

    @Override
    public Integer updateMaster(String did, String phone, Integer buildingid) {
        return userDao.updateMaster(did, phone, buildingid);
    }

    @Override
    public Integer updateStu(String uid, String phone, String college, String major, String classes) {
        return userDao.updateStu(uid, phone, college, major, classes);
    }

    @Override
    public Integer updateUserPw(String username, String password) {
        return userDao.updateUserPw(username,password);
    }

    @Override
    public Integer updatePermissions(String buildingid, String did) {
        return userDao.updatePermissions(buildingid, did);
    }

    @Override
    public List<Master> selAllMaster() {
        return userDao.selAllMaster();
    }
}
