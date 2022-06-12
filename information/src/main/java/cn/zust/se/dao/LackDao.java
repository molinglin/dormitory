package cn.zust.se.dao;

import cn.zust.se.eneity.Building;
import cn.zust.se.eneity.Lack;
import cn.zust.se.eneity.Lacks;
import cn.zust.se.eneity.Master;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface LackDao {
    @Insert("insert into lack(uid,time,buildingid,dormitory,intro) values(#{uid},#{time},#{buildingid},#{dormitory},#{intro})")
    Integer insertLack(@Param("uid") String uid, @Param("time") Date time,@Param("buildingid") Integer buildingid, @Param("dormitory") String dormitory,@Param("intro")String intro);
    @Select("select * from lack order by time desc")
    List<Lack> selAllLack();
    @Select("select * from (select * from lack order by time desc)temp1 where uid=#{uid}")
    List<Lack> selLackByUid(String uid);
    @Select("select * from (select * from lack order by time desc)temp1 where buildingid=#{buildingid}")
    List<Lack> selLackByBuilding(Integer buildingid);
    @Select("select * from lack where id=#{id}")
    Lack selL(Integer id);
    @Select("select * from building where id=#{id}")
    Building selB(Integer id);
    @Delete("delete from lack where id=#{id}")
    Integer delLack(Integer id);
    @Select("select lack.id,stu.`name`,lack.time,lack.buildingid,lack.dormitory,lack.intro\n" +
            "FROM lack,stu\n" +
            "WHERE (stu.name=#{name} or #{name} is null) and (lack.dormitory=#{dormitory} or #{dormitory} is null) and (lack.buildingid=#{buildingid} or #{buildingid} is null) and (lack.time between #{time1} and #{time2}) AND (lack.uid=stu.uid) ORDER BY lack.time")
    List<Lacks> selLacks(@Param("name") String name, @Param("time1") Date time1, @Param("time2") Date time2, @Param("buildingid") Integer buildingid, @Param("dormitory") String dormitory);
}

//    select lack.id,stu.`name`,lack.time,lack.buildingid,lack.dormitory,lack.intro
//        FROM lack,stu
//        WHERE stu.`name`="zs" AND lack.uid=stu.uid;
