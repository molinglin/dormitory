package cn.zust.se.service.impl;

import cn.zust.se.dao.ApplicationDao;
import cn.zust.se.eneity.Application;
import cn.zust.se.eneity.Repair;
import cn.zust.se.service.ApplicationService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationServiceimpl implements ApplicationService {
    @Autowired
    ApplicationDao applicationDao;
    @Override
    public int changeDormitory(Application application) {
        application.setType(0);
        application.setAccess(0);
        return applicationDao.insert(application);
    }

    @Override
    public int quitDormitory(Application application) {
        application.setType(1);
        application.setAccess(0);
        return applicationDao.insert(application);
    }

    @Override
    public int cancel(Integer id) {
        return applicationDao.delete(id);
    }

    @Override
    public List<Application> selects() {
        return applicationDao.selects();
    }

    @Override
    public List<Application> selectsNoAccess() {
        List<Application> applications = applicationDao.selectNoAccess(0);
        return applications;
    }
    @Override
    public List<Repair> selRepair()
    {
        return applicationDao.selRepair();
    }
    @Override
    public List<Application> selectsAccess() {
        List<Application> applications = applicationDao.selects();
        List<Application> Access=new ArrayList<>();
        for (Application application:applications) {
            if(application.getAccess()==1)
                Access.add(application);
        }
        return Access;
    }

    @Override
    public List<Application> selectByUid(Integer uid) {
        return applicationDao.selectByUid(uid);
    }

    @Override
    public Application selectById(Integer id) {
        return applicationDao.selectById(id);
    }

    @Override
    public int agree(Integer id) {
        return applicationDao.updateAccess(id, 1);

    }

    @Override
    public int reject(Integer id) {
        return applicationDao.updateAccess(id,2);
    }


}
