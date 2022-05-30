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
    @Delete("delete * from stu where id=#{id}")
    int delete(Integer id);
    @Update("update stu set dormitory=#{dormitory} where id=#{id}")
    int updateDormitory(@Param("id") Integer id,@Param("dormitory") String dormitory);//修改寝室

}
