package cn.zust.se.service;

import cn.zust.se.eneity.Bed;

public interface BedService {
    Bed selEmptyBeds();
    Bed selEmptyBedsByBuilding(Integer buildingid);
    Bed selEmptyBedsByFloor(String floor);
}
