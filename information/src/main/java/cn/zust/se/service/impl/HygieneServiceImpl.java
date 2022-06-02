package cn.zust.se.service.impl;

import cn.zust.se.dao.HygieneDao;
import cn.zust.se.eneity.Hygiene;
import cn.zust.se.service.HygieneService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class HygieneServiceImpl implements HygieneService {
    @Resource
    HygieneDao hygieneDao;

    @Override
    public List<Hygiene> selAllHy() {
        return hygieneDao.selAllHy();
    }

    @Override
    public List<Hygiene> selHyByBuilding(Integer buildingid) {
        return hygieneDao.selHyByBuilding(buildingid);
    }

    @Override
    public List<Hygiene> selHyByTimes(Integer times) {
        return hygieneDao.selHyByTimes(times);
    }

    @Override
    public List<Hygiene> selHyByBAndT(Integer buildingid, Integer times) {
        return hygieneDao.selHyByBAndT(buildingid, times);
    }

    @Override
    public List<Hygiene> selHyByBAndD(Integer buildingid, String dormitory) {
        return hygieneDao.selHyByBAndD(buildingid, dormitory);
    }

    @Override
    public List<Hygiene> selHyByBAndTAndD(Integer buildingid, Integer times, String dormitory) {
        return hygieneDao.selHyByBAndTAndD(buildingid, times, dormitory);
    }
}
