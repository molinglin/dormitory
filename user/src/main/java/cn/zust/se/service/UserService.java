package cn.zust.se.service;

import cn.zust.se.eneity.User;

public interface UserService {
    User login(String username);
    Integer updateUserPw(String username,String password);
    Integer updateStu(String uid,String phone,String college,String major,String classes);
    Integer updateMaster(String did,String phone,Integer building_id);
}
