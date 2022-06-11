package cn.zust.se.dao;

import cn.zust.se.eneity.Question;
import cn.zust.se.eneity.Questionnaire;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionDao {
    @Insert("insert into questionnaire values(#{uid},#{sleep},#{getup},#{temper})")
    Integer insertQ(@Param("uid") String uid,@Param("sleep") Integer sleep,@Param("getup") Integer getup,@Param("smoke") Integer temper);
    @Select("select * from questionnaire where uid=#{uid}")
    List<Question> selQ(String uid);
    @Select("select questionnaire.uid,stu.name,questionnaire.sleep,questionnaire.getup,questionnaire.temper from questionnaire,stu " +
            "where questionnaire.uid=stu.uid and questionnaire.uid in (select stu.uid where stu.name=#{name})")
    List<Questionnaire> selQByName(String name);

}
