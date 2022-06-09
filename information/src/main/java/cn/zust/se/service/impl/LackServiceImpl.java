package cn.zust.se.service.impl;

import cn.zust.se.dao.LackDao;
import cn.zust.se.eneity.Lack;
import cn.zust.se.eneity.Lacks;
import cn.zust.se.service.LackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Service
public class LackServiceImpl implements LackService {
    @Resource
    LackDao lackDao;

    @Override
    public Integer insertLack(String uid, Date time, Integer buildingid, String dormitory,String intro) {
        return lackDao.insertLack(uid, time, buildingid, dormitory,intro);
    }

    @Override
    public List<Lack> selAllLack() {
        return lackDao.selAllLack();
    }

    @Override
    public List<Lack> selLackByUid(String uid) {
        return lackDao.selLackByUid(uid);
    }

    @Override
    public List<Lack> selLackByBuilding(Integer buildingid) {
        return lackDao.selLackByBuilding(buildingid);
    }

    @Override
    public Integer delLack(Integer id) {
        return lackDao.delLack(id);
    }

    @Override
    public List<Lacks> selLacks(String name, Date time1, Date time2, Integer buildingid, String dormitory) {
        return lackDao.selLacks(name, time1, time2, buildingid, dormitory);
    }
}
