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
    @Select("select * from stu where uid like CONCAT('%',#{uid},'%')")
    List<Stu> getStuByUid(String  uid);
    @Select("select * from stu where name like CONCAT('%',#{name},'%')")
    List<Stu> getStuByName(String name);
    @Select("select * from stu where dormitory like CONCAT('%',#{dormitory},'%')")
    List<Stu> getStudByDormitory(String dormitory);//通过寝室号找学生
    @Select("select * from stu where uid like CONCAT('%',#{uid},'%') and name like CONCAT('%',#{name},'%')")
    List<Stu> getStuByUandN(@Param("uid") String uid,@Param("name") String name);
    @Select("select * from stu where uid like CONCAT('%',#{uid},'%') and dormitory like CONCAT('%',#{dormitory},'%')")
    List<Stu> getStuByUandD(@Param("uid") String uid,@Param("dormitory") String dormitory);
    @Select("select * from stu where name like CONCAT('%',#{name},'%') and dormitory like CONCAT('%',#{dormitory},'%')")
    List<Stu> getStuByNandD(@Param("name") String name,@Param("dormitory") String dormitory);
    @Select("select * from stu where uid like CONCAT('%',#{uid},'%') name like CONCAT('%',#{name},'%') and dormitory like CONCAT('%',#{dormitory},'%')")
    List<Stu> getStuByAll(@Param("uid") String uid,@Param("name") String name,@Param("dormitory") String dormitory);
    @Select("select * from stu where gender ='男' and status=0 order by college")
    List<Stu> getStudnan();
    @Select("select * from stu where gender ='女' and status=0 order by college")
    List<Stu> getStudnv();
    int create(Stu stu);
    int insertList(List<Stu> list);
    @Delete("delete  from stu where id=#{id}")
    int delete(Integer id);
    @Update("update stu set dormitory=#{dormitory} where id=#{id}")
    int updateDormitory(@Param("id") Integer id,@Param("dormitory") String dormitory);//修改寝室
    @Update("update stu set buildingid=#{buildingid} where id=#{id}")
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
    @Update("update stu set uid=#{uid} and name=#{name} and gender=#{gender} and phone=#{phone}" +
            "and college=#{college} and major=#{major} and classes=#{classes} and dormitory=#{dormitory}" +
            "and buildingid=#{buildingid} and bednum=#{bednum} and status=#{status} where id=#{id}")
    int update(Stu stu);
}
