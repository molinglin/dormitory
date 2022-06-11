package cn.zust.se.dao;

import cn.zust.se.eneity.Inform;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface InformDao {
    Integer insertInform(Inform inform);
    @Update("update inform set title=#{title},content=#{content} where id=#{id}")
    Integer updateInform(@Param("id") Integer id,@Param("title") String title,@Param("content") String content);
    @Delete("delete from inform where id=#{id}")
    Integer delInform(Integer id);
    @Select("select * from inform")
    List<Inform> selAllInform();
    @Select("select * from inform where id=#{id}")
    Inform selInform(Integer id);
    @Select("select * from inform where publisher=#{publisher}")
    List<Inform> selInformByPublisher(String publisher);
    @Select("select * from inform where time > #{time}")
    List<Inform> selInformByTime(Date time);
    @Select("select * from inform where publisher=#{publisher} and time > #{time}")
    List<Inform> selInformByPAndT(@Param("publisher") String publisher,@Param("time") Date time);
    @Select("select * from inform where (publisher=#{publisher} or #{publisher} is null) and (time > #{time} or #{time} is null) order by time desc")
    List<Inform> selInforms(@Param("publisher") String publisher,@Param("time") Date time);

}
