package cn.zust.se.dao;

import cn.zust.se.eneity.Bed;
import cn.zust.se.eneity.Building;
import cn.zust.se.eneity.Stu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface BedDao {
    @Select("select * from bed where buildingid=#{buildingid} and dormitory=#{dormitory} and bednum=#{bednum}")
    Bed getBed(@Param("buildingid") String buildingid,@Param("dormitory") String dormitory,@Param("bednum") Integer bednum);
    @Select("select * from stu where uid=#{uid}")
    Stu getStuByUid(String  uid);
    @Select("select * from bed where empty='Y'")
    List<Bed> selEmptyBeds();
    @Select("select * from bed where buildingid=#{buildingid} and empty='Y'")
    List<Bed> selEmptyBedsByBuilding(Integer buildingid);
    @Select("select * from bed where dormitory like #{floor} and empty='Y'")
    List<Bed> selEmptyBedsByFloor(String floor);
    @Select("select * from bed where buildingid=#{buildingid} and empty='Y' and dormitory like #{floor}")
    List<Bed> selEmptyBedsByBAndF(@Param("buildingid") Integer buildingid,@Param("floor") String floor);
    @Select("select * from bed")
    List<Bed> selAllBeds();
    @Select("select * from bed where buildingid=#{buildingid}")
    List<Bed> selBedsByBuilding(Integer buildingid);
    @Select("select * from bed where dormitory like #{floor}")
    List<Bed> selBedsByFloor(String floor);
    @Select("select * from bed where buildingid=#{buildingid} and dormitory like #{floor}")
    List<Bed> selBedsByBAndF(@Param("buildingid") Integer buildingid,@Param("floor") String floor);
    @Select("select * from bed " +
            "where (bed.buildingid=#{buildingid} or #{buildingid} is null) and (bed.dormitory=#{dormitory} or #{dormitory} is null) and " +
            "(bed.bednum=#{bednum} or #{bednum} is null)  order by bid asc")
    List<Bed> selBeds(@Param("buildingid") String buildingid, @Param("dormitory") String dormitory,@Param("bednum") Integer bednum);
    @Select("select * from building")
    List<Building> selBuildings();
//    @Update("update bed set empty=#{empty} and uid=#{uid} where buildingid=#{buildingid} and dormitory=#{dormitory} and bednum=#{bednum}")
    int updateUandE(@Param("empty") String empty,@Param("uid") String uid,@Param("buildingid") String building,@Param("dormitory") String dormitory,@Param("bednum") Integer bednum);
}

//    and (bed.empty=#{empty} or #{empty} is null)
//,@Param("empty") String empty
//and (bed.uid=stu.uid ) and (bed.empty=#{empty} or #{empty} is null)
