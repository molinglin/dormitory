package cn.zust.se.service.impl;

import cn.zust.se.dao.BedDao;
import cn.zust.se.eneity.Bed;
import cn.zust.se.service.BedService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BedServiceImpl implements BedService {
    @Resource
    BedDao bedDao;

    @Override
    public List<Bed> selEmptyBeds() {
        return bedDao.selEmptyBeds();
    }

    @Override
    public List<Bed> selEmptyBedsByBuilding(Integer buildingid) {
        return bedDao.selEmptyBedsByBuilding(buildingid);
    }

    @Override
    public List<Bed> selEmptyBedsByFloor(String floor) {
        String fl1=floor+"%";
        return bedDao.selEmptyBedsByFloor(fl1);
    }

    @Override
    public List<Bed> selAllBeds() {
        return bedDao.selAllBeds();
    }

    @Override
    public List<Bed> selBedsByBuilding(Integer buildingid) {
        return bedDao.selBedsByBuilding(buildingid);
    }

    @Override
    public List<Bed> selEmptyBedsByBAndF(Integer buildingid, String floor) {
        String fl2=floor+"%";
        return bedDao.selEmptyBedsByBAndF(buildingid,fl2);
    }

    @Override
    public List<Bed> selBedsByFloor(String floor) {
        String fl3=floor+"%";
        return bedDao.selBedsByFloor(fl3);
    }

    @Override
    public List<Bed> selBedsByBAndF(Integer buildingid, String floor) {
        String fl4=floor+"%";
        return bedDao.selBedsByBAndF(buildingid,fl4);
    }
}
