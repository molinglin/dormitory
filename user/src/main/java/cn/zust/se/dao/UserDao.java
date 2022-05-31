package cn.zust.se.dao;

import cn.zust.se.eneity.Master;
import cn.zust.se.eneity.Stu;
import cn.zust.se.eneity.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    @Select("select * from user where username=#{username}")
    User login(String username);
    @Update("update stu set phone=#{phone},college=#{college},major=#{major},classes=#{classes} where uid=#{uid}")
    Integer updateStu(@Param("uid") String uid,@Param("phone") String phone,@Param("college") String college,@Param("major") String major,@Param("classes") String classes);
    @Update("update user set password=#{password} where username=#{username}")
    Integer updateUserPw(@Param("username") String username,@Param("password") String password);
    @Update("update master set phone=#{phone},building_id=#{building_id} where did=#{did}")
    Integer updateMaster(@Param("did") String did,@Param("phone") String phone,@Param("building_id") Integer building_id);
    @Select("select * from stu where uid=#{uid}")
    Stu selStu(String uid);
    @Select("select * from master where did=#{did}")
    Master selMaster(String did);
}
