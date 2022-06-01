package cn.zust.se.dao;

import cn.zust.se.eneity.Application;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ApplicationDao {
    @Select("select * from application where id=#{id}")
    Application selectApplication(Integer id);
    @Select("select * from application")
    List<Application> selects();

    int insert(Application application);
    @Update("update application set access=#{access} where id=#{id}")
    int updateAccess(@Param("id") Integer id,@Param("access") Integer access);
    @Delete("delete * from application where id=#{id}")
    int delete(Integer id);
}
