package cn.zust.se.service;

import cn.zust.se.eneity.Bed;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BedService {
    List<Bed> selEmptyBeds();
    List<Bed> selEmptyBedsByBuilding(Integer buildingid);
    List<Bed> selEmptyBedsByFloor(String floor);
    List<Bed> selEmptyBedsByBAndF(Integer buildingid,String floor);
    List<Bed> selAllBeds();
    List<Bed> selBedsByBuilding(Integer buildingid);
    List<Bed> selBedsByFloor(String floor);
    List<Bed> selBedsByBAndF(Integer buildingid,String floor);
}
