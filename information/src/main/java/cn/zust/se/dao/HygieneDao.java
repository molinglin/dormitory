package cn.zust.se.dao;

import cn.zust.se.eneity.Hygiene;
import org.apache.ibatis.annotations.Insert;
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
    @Insert("insert into hygiene(times,buildingid,dormitory,result) values (#{times},#{buildingid},#{dormitory},#{result}) ")
    Integer insertHy(@Param("times") Integer times,@Param("buildingid")Integer buildingid,@Param("dormitory")String dormitory,@Param("result")Integer result);
    Integer insertHyByExcel(List<Hygiene> hygiene);
    @Select("select * from hygiene where (times=#{times} or #{times} is null) and (buildingid=#{buildingid} or #{buildingid} is null) and (dormitory=#{dormitory} or #{dormitory} is null) and (result between #{result1} and #{result2})")
    List<Hygiene> selHygiene(@Param("times") Integer times,@Param("buildingid") Integer buildingid,@Param("dormitory") String dormitory,@Param("result1") Integer result1,@Param("result2") Integer result2);
}
