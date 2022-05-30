package cn.zust.se.dao;

import cn.zust.se.eneity.Stu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StuDao {

    @Select("select * from stu where id=#{id}")
    Stu getStuById(Integer id);
    @Select("select * from stu")
    List<Stu> getsAll();
    @Select("select * from stu where uid=#{uid}")
    Stu getStuByUid(Integer uid);
    @Select("select * from stu where name=#{name}")
    Stu getStuByName(String name);
}
