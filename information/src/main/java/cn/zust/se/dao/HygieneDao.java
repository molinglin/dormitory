package cn.zust.se.dao;

import cn.zust.se.eneity.Hygiene;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HygieneDao {
    @Select("select * from hygiene")
    List<Hygiene> selAllHy();
    @Select("select * from hygiene where buildingid=#{buildingid}")
    List<Hygiene> selHyByBuilding(Integer buildingid);
    @Select("select * from hygiene where times=#{times}")
    List<Hygiene> selHyByTimes(Integer times);
    @Select("select * from hygiene where buildingid=#{buildingid} and times=#{times}")
    List<Hygiene> selHyByBAndT(@Param("buildingid") Integer buildingid,@Param("times") Integer times);
    @Select("select * from hygiene where buildingid=#{buildingid} and dormitory=#{dormitory}")
    List<Hygiene> selHyByBAndD(@Param("buildingid")Integer buildingid,@Param("dormitory")String dormitory);
    @Select("select * from hygiene where buildingid=#{buildingid} and times=#{times} and dormitory=#{dormitory}")
    List<Hygiene> selHyByBAndTAndD(@Param("buildingid") Integer buildingid,@Param("times") Integer times,@Param("dormitory") String dormitory);
}
