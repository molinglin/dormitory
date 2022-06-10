package cn.zust.se.service;

import cn.zust.se.eneity.Inform;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface InformService {
    Integer insertInform(String publisher, String content,String title);
    List<Inform> selAllInform();
    List<Inform> selInformByPublisher(String publisher);
    List<Inform> selInformByTime(Date time);
    List<Inform> selInformByPAndT(String publisher,Date time);
    List<Inform> selInforms(String publisher,Date time);
}
