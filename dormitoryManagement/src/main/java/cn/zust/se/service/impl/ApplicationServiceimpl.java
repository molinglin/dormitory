package cn.zust.se.service.impl;

import cn.zust.se.dao.ApplicationDao;
import cn.zust.se.eneity.Application;
import cn.zust.se.service.ApplicationService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceimpl implements ApplicationService {
    @Autowired
    ApplicationDao applicationDao;
    @Override
    public int changeDormitory(Application application) {
        application.setType(0);
        return applicationDao.insert(application);
    }

    @Override
    public int quitDormitory(Application application) {
        application.setType(1);
        return applicationDao.insert(application);
    }

    @Override
    public int cancel(Integer id) {
        return applicationDao.delete(id);
    }


}
