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
//    @Insert("insert into questionnaire(uid,sleep,getup,temper) values(#{uid},#{sleep},#{getup},#{temper})")
    Integer insertQ(Question question);
    @Select("select * from questionnaire where uid=#{uid}")
    List<Question> selQ(String uid);
    @Select("select questionnaire.uid,stu.name,questionnaire.sleep,questionnaire.getup,questionnaire.temper from questionnaire,stu " +
            "where questionnaire.uid=stu.uid and questionnaire.uid in (select stu.uid where stu.name=#{name})")
    List<Questionnaire> selQByName(String name);

}
