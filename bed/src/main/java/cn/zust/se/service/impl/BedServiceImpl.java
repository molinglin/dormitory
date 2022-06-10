package cn.zust.se.service.impl;

import cn.zust.se.dao.BedDao;
import cn.zust.se.eneity.Bed;
import cn.zust.se.eneity.Building;
import cn.zust.se.service.BedService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public List<Building> selBuildings() {
        return bedDao.selBuildings();
    }

    @Override
    public List<Bed> selBeds(String buildingid, String dormitory, String bedNum, String name, String empty) {
        List<Bed> bed=bedDao.selBeds(buildingid, dormitory, bedNum);
//        System.out.println(bed);
//        List<Beds> beds=new ArrayList<>();
        for (int i=0;i<bed.size();i++){
            if (bed.get(i).getUid() == null || Objects.equals(bed.get(i).getUid(), "")) {
                bed.get(i).setUid("");
            } else {
                String name1=bedDao.getStuByUid(bed.get(i).getUid()).getName();
                bed.get(i).setUid(name1);
            }
        }
        for (int i=0;i<bed.size();i++){
            if(name!=null && Objects.equals(bed.get(i).getUid(), name) ){
                List<Bed> bed1=new ArrayList<>();
                bed1.add(bed.get(i));
                return bed1;
            }
        }

        List<Bed> bed2=new ArrayList<>();
        if(Objects.equals(empty, "Y")) {
            for (int i = 0; i < bed.size(); i++) {
                if (Objects.equals(bed.get(i).getEmpty(), "Y"))
                    bed2.add(bed.get(i));
            }
            return bed2;
        }else if (Objects.equals(empty,"N")) {
            for (int i = 0; i < bed.size(); i++) {
                if (Objects.equals(bed.get(i).getEmpty(), "N"))
                    bed2.add(bed.get(i));
            }
            return bed2;
        }
            else {
                return bed;
            }
//        return bedDao.selBeds(buildingid, dormitory, bedNum);
    }

//    @Override
//    public List<Bed> selBedss(String buildingid, String dormitory, String bedNum) {
//        List<Bed> bed=bedDao.selBeds(buildingid, dormitory, bedNum);
//        List<Bed> beds=new ArrayList<>();
//        beds.addAll(bed);
////        System.out.println(bed.size());
//        for (int i=0;i<bed.size();i++){
//            if (Objects.equals(bed.get(i).getUid(), "") || bed.get(i)==null) {
//                beds.get(i).setUid("");
//            } else {
//                String name1=bedDao.getStuByUid(bed.get(i).getUid()).getName();
//                beds.get(i).setUid(name1);
//            }
//        }
//        return beds;
//    }
}
