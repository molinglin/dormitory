package cn.zust.se.dao;

import cn.zust.se.eneity.Bed;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BedDao {
    @Select("select * from bed where empty=0")
    Bed selEmptyBeds();
    @Select("select * from bed where buildingid=#{buildingid} and empty=0")
    Bed selEmptyBedsByBuilding(Integer buildingid);
    @Select("select * from bed where dormitory like #{floor}")
    Bed selEmptyBedsByFloor(String floor);
}
