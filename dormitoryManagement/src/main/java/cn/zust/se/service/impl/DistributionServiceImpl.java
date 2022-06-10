package cn.zust.se.service.impl;

import cn.zust.se.dao.DistributionDao;
import cn.zust.se.eneity.Distribution;
import cn.zust.se.eneity.EmptyBeds;
import cn.zust.se.eneity.Stu;
import cn.zust.se.service.DistributionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DistributionServiceImpl implements DistributionService {
    @Resource
    DistributionDao distributionDao;

    @Override
    public List<Distribution> distributionB() {
        List<Distribution> distributions=distributionDao.selAllBD();
        List<EmptyBeds> emptyBeds=distributionDao.selEmptyBBeds();
        return getDistributions(distributions, emptyBeds);
    }

    @Override
    public List<Distribution> distributionG() {
        List<Distribution> distributions=distributionDao.selAllGD();
        List<EmptyBeds> emptyBeds=distributionDao.selEmptyGBeds();
        return getDistributions(distributions, emptyBeds);
    }

    private List<Distribution> getDistributions(List<Distribution> distributions, List<EmptyBeds> emptyBeds) {
        if(distributions.size()>emptyBeds.size()){
            return null;
        }else {
            for (int i=0;i<distributions.size();i++){
                distributions.get(i).setBid(emptyBeds.get(i).getBid());
                distributions.get(i).setBuildingid(emptyBeds.get(i).getBuildingid());
                distributions.get(i).setDormitory(emptyBeds.get(i).getDormitory());
                distributions.get(i).setBednum(emptyBeds.get(i).getBednum());
            }
            return distributions;
        }
    }

    @Override
    public Integer updateBed(String uid, String bid) {
        return distributionDao.updateBed(uid, bid);
    }

    @Override
    public Integer updateStu(String uid, String buildingid, String dormitory, String bednum) {
        return distributionDao.updateStu(uid, buildingid, dormitory, bednum);
    }

    @Override
    public List<Stu> selBStu() {
        return distributionDao.selBStu();
    }

    @Override
    public List<Stu> selGStu() {
        return distributionDao.selGStu();
    }
}
