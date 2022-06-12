package cn.zust.se.dao;

import cn.zust.se.eneity.Stu;
import cn.zust.se.eneity.StuApplication;
import cn.zust.se.service.StuApplicationService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StuApplicationDao {

    int insert(@Param("uid1") String uid1,@Param("uid2") String uid2,@Param("applicationid") Integer applicationid,@Param("access") Integer access);
    @Select("select * from stuapplication where id=#{id}")
    StuApplication findById(Integer id);
    @Select("select * from stuapplication where access=#{access}")
    List<StuApplication> findByAccess(Integer access);
    @Select("select * from stuapplication where uid2=#{uid}")
    List<StuApplication> findByUid2(Integer uid);
    @Select("select * from stuapplication where uid1=#{uid}")
    List<StuApplication> findByUid1(Integer uid);
    @Update("update stuapplication set access=#{access} where id=#{id}")
    int updateAccess(@Param("access") Integer access,@Param("id") Integer id);
}
