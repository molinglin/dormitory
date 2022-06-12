package cn.zust.se.service.impl;

import cn.zust.se.dao.StuApplicationDao;
import cn.zust.se.eneity.StuApplication;
import cn.zust.se.service.StuApplicationService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StuApplicationServiceImpl implements StuApplicationService {
    @Autowired
    StuApplicationDao stuApplicationDao;
    @Override
    public int insert(String uid1,String uid2,Integer applicationid, Integer access) {
        return stuApplicationDao.insert(uid1,uid2,applicationid,access);
    }

    @Override
    public List<StuApplication> findNoAccess() {
        return stuApplicationDao.findByAccess(0);
    }

    @Override
    public List<StuApplication> findAccess() {
        return stuApplicationDao.findByAccess(1);
    }

    @Override
    public List<StuApplication> findByUid2(Integer uid) {
        return stuApplicationDao.findByUid2(uid);
    }

    @Override
    public List<StuApplication> findByUid1(Integer uid) {
        return stuApplicationDao.findByUid1(uid);
    }

    @Override
    public int agree(Integer id) {
        return stuApplicationDao.updateAccess(1,id);
    }

    @Override
    public int reject(Integer id) {
        return stuApplicationDao.updateAccess(2,id);
    }

    @Override
    public StuApplication findById(Integer id) {
        return stuApplicationDao.findById(id);
    }
}
