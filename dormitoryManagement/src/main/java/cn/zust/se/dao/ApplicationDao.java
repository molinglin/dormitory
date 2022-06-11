package cn.zust.se.dao;

import cn.zust.se.eneity.Application;
import cn.zust.se.eneity.Repair;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;
import org.checkerframework.checker.units.qual.A;

import java.util.List;
@Mapper
public interface ApplicationDao {
    @Select("select * from application where id=#{id}")
    Application selectApplication(Integer id);
    @Select("select * from application")
    List<Application> selects();
    @Select("select * from application where uid=#{uid}")
    List<Application> selectByUid(Integer uid);
    @Select("select * from application where id=#{id}")
    Application selectById(Integer id);
    @Select("select * from application where access=#{access}")
    List<Application> selectNoAccess(Integer access);

    int insert(Application application);

    int insertRepair(Repair repair);
    @Select("select * from repair")
    List<Repair> selRepair();
    @Update("update application set access=#{access} where id=#{id}")
    int updateAccess(@Param("id") Integer id,@Param("access") Integer access);
    @Update("update repair set complete=#{complete} where id=#{id}")
    int upRepair(@Param("id") Integer id,@Param("complete") String complete);
    @Delete("delete  from application where id=#{id}")
    int delete(Integer id);

}
