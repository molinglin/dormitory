package cn.zust.se.service;

import cn.zust.se.dao.ApplicationDao;
import cn.zust.se.eneity.Application;

public interface ApplicationService {

    int changeDormitory(Application application);//换宿
    int quitDormitory(Application application);//退宿
    int cancel(Integer id);//取消申请
}
