package cn.zust.se.dao;

import cn.zust.se.eneity.Application;
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
    int insert(Application application);
    @Update("update application set access=#{access} where id=#{id}")
    int updateAccess(@Param("id") Integer id,@Param("access") Integer access);
    @Delete("delete  from application where id=#{id}")
    int delete(Integer id);
}
