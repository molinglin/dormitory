package cn.zust.se.dao;

import cn.zust.se.eneity.Stu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StuDao {

    @Select("select * from stu where id=#{id}")
    Stu getStuById(Integer id);
    @Select("select * from stu")
    List<Stu> getsAll();
    @Select("select * from stu where uid=#{uid}")
    Stu getStuByUid(String  uid);
    @Select("select * from stu where name=#{name}")
    Stu getStuByName(String name);
    @Select("select * from stu where dormitory=#{dormitory}")
    List<Stu> getStudByDormitory(String dormitory);//通过寝室号找学生
    int create(Stu stu);
    int insertList(List<Stu> list);
    @Delete("delete  from stu where id=#{id}")
    int delete(Integer id);
    @Update("update stu set dormitory=#{dormitory} where id=#{id}")
    int updateDormitory(@Param("id") Integer id,@Param("dormitory") String dormitory);//修改寝室
    @Update("update stu set buildingid=#{building} where id=#{id}")
    int updateBuildingId(@Param("id") Integer id,@Param("buildingid") Integer buildingid);
    @Update("update stu set bednum=#{bednum} where id=#{id}")
    int updateBedNum(@Param("id") Integer id,@Param("bednum") Integer bednum);
    @Update("update stu set gender=#{gender} where id=#{id}")
    int updateGender(@Param("id") Integer id,@Param("gender") Integer gender);
    @Update("update stu set name=#{name} where id=#{id}")
    int updateName(@Param("id") Integer id,@Param("name") String name);
    @Update("update stu set uid=#{uid} where id=#{id}")
    int updateUid(@Param("id") Integer id,@Param("uid") String uid);
    @Update("update stu set phone=#{phone} where id=#{id}")
    int updatePhone(@Param("id") Integer id,@Param("uid") String phone);
    @Update("update stu set college=#{college} where id=#{id}")
    int updateCollege(@Param("id") Integer id,@Param("uid") String college);
}
