package cn.zust.se.service.impl;

import cn.zust.se.dao.BedDao;
import cn.zust.se.eneity.Bed;
import cn.zust.se.service.BedService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BedServiceImpl implements BedService {
    @Resource
    BedDao bedDao;
    @Override
    public Bed selEmptyBeds() {
        return bedDao.selEmptyBeds();
    }

    @Override
    public Bed selEmptyBedsByBuilding(Integer buildingid) {
        return bedDao.selEmptyBedsByBuilding(buildingid);
    }

    @Override
    public Bed selEmptyBedsByFloor(String floor) {
        String fl=floor+"%";
        return bedDao.selEmptyBedsByFloor(fl);
    }
}
