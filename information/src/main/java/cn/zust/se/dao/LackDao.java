package cn.zust.se.dao;

import cn.zust.se.eneity.Lack;
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
    @Delete("delete from lack where id=#{id}")
    Integer delLack(Integer id);
}
