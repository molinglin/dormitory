package cn.zust.se.service;

import cn.zust.se.dao.ApplicationDao;
import cn.zust.se.eneity.Application;

import java.util.List;

public interface ApplicationService {

    int changeDormitory(Application application);//换宿
    int quitDormitory(Application application);//退宿
    int cancel(Integer id);//取消申请
    List<Application> selects();//所有请求
    List<Application> selectsNoAccess();//未通过请求
    List<Application> selectsAccess();//已通过请求
    List<Application> selectByUid(Integer uid);
    Application selectById(Integer id);
}
