package cn.zust.se.service;

import cn.zust.se.eneity.Bed;
import cn.zust.se.eneity.Building;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BedService {
    Bed getBed(String building, String dormitory, Integer bednum);
    List<Bed> selEmptyBeds();
    List<Bed> selEmptyBedsByBuilding(Integer buildingid);
    List<Bed> selEmptyBedsByFloor(String floor);
    List<Bed> selEmptyBedsByBAndF(Integer buildingid,String floor);
    List<Bed> selAllBeds();
    List<Bed> selBedsByBuilding(Integer buildingid);
    List<Bed> selBedsByFloor(String floor);
    List<Bed> selBedsByBAndF(Integer buildingid,String floor);
    List<Bed> selBeds(String buildingid, String dormitory, Integer bednum, String name, String empty);
    List<Building> selBuildings();
    int updateUandE(String empty, String uid ,String buildingid, String dormitory,Integer bednum);
//    List<Bed> selBedss(String buildingid, String dormitory, String bednum);
}
