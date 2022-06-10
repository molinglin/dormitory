package cn.zust.se.service;

import cn.zust.se.eneity.Distribution;
import cn.zust.se.eneity.EmptyBeds;
import cn.zust.se.eneity.Stu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DistributionService {
    List<Distribution> distributionB();
    List<Distribution> distributionG();
    Integer updateBed(String uid,String bid);
    Integer updateStu(String uid,String buildingid,String dormitory, String bednum);
    List<Stu> selBStu();
    List<Stu> selGStu();
}
