package cn.zust.se.service;

import cn.zust.se.eneity.Hygiene;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HygieneService {
    List<Hygiene> selAllHy();
    List<Hygiene> selHyByBuilding(Integer buildingid);
    List<Hygiene> selHyByTimes(Integer times);
    List<Hygiene> selHyByBAndT(Integer buildingid,Integer times);
    List<Hygiene> selHyByBAndD(Integer buildingid,String dormitory);
    List<Hygiene> selHyByBAndTAndD(Integer buildingid,Integer times,String dormitory);
}
