package cn.zust.se.dao;

import cn.zust.se.eneity.Bed;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BedDao {
    @Select("select * from bed where empty=0")
    List<Bed> selEmptyBeds();
    @Select("select * from bed where buildingid=#{buildingid} and empty=0")
    List<Bed> selEmptyBedsByBuilding(Integer buildingid);
    @Select("select * from bed where dormitory like #{floor} and empty=0")
    List<Bed> selEmptyBedsByFloor(String floor);
    @Select("select * from bed where buildingid=#{buildingid} and empty=0 and dormitory like #{floor}")
    List<Bed> selEmptyBedsByBAndF(@Param("buildingid") Integer buildingid,@Param("floor") String floor);
    @Select("select * from bed")
    List<Bed> selAllBeds();
    @Select("select * from bed where buildingid=#{buildingid}")
    List<Bed> selBedsByBuilding(Integer buildingid);
    @Select("select * from bed where dormitory like #{floor}")
    List<Bed> selBedsByFloor(String floor);
    @Select("select * from bed where buildingid=#{buildingid} and dormitory like #{floor}")
    List<Bed> selBedsByBAndF(@Param("buildingid") Integer buildingid,@Param("floor") String floor);
    List<Bed> selBeds();
}
