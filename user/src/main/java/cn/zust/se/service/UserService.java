package cn.zust.se.service;

import cn.zust.se.eneity.Master;
import cn.zust.se.eneity.Stu;
import cn.zust.se.eneity.User;

import java.util.List;

public interface UserService {
    User login(String username);
    Integer updateUserPw(String username,String password);
    Integer updateStu(String uid,String phone,String college,String major,String classes);
    Integer updateMaster(String did,String phone,Integer buildingid);
    Stu selStu(String uid);
    Master selMaster(String did);
    Integer updatePermissions(String buildingid,String did);
    List<Master> selAllMaster();

}
