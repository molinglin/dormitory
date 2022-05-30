package cn.zust.se.dao;

import cn.zust.se.eneity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface userDao {
    @Select("select * from user where username=#{username}")
    User login(String username,String password);
}
