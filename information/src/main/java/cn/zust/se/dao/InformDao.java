package cn.zust.se.dao;

import cn.zust.se.eneity.Inform;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface InformDao {
    @Insert("insert into inform (time,publisher,content) values(#{time},#{publisher},#{content})")
    Integer insertInform(@Param("time") Date time,@Param("publisher") String publisher,@Param("content")String content);
    @Select("select * from inform")
    List<Inform> selAllInform();
    @Select("select * from inform where publisher=#{publisher}")
    List<Inform> selInformByPublisher(String publisher);
    @Select("select * from inform where time > #{time}")
    List<Inform> selInformByTime(Date time);
    @Select("select * from inform where publisher=#{publisher} and time > #{time}")
    List<Inform> selInformByPAndT(@Param("publisher") String publisher,@Param("time") Date time);
}
