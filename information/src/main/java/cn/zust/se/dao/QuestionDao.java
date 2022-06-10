package cn.zust.se.dao;

import cn.zust.se.eneity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionDao {
    @Insert("insert into questionnaire values(#{uid},#{sleep},#{getup},#{smoke})")
    Integer insertQ(@Param("uid") String uid,@Param("sleep") Integer sleep,@Param("getup") Integer getup,@Param("smoke") Integer smoke);
    @Select("select * from questionnaire where uid=#{uid}")
    List<Question> selQ(String uid);
}
