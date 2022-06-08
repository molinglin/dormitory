package cn.zust.se.service;

import cn.zust.se.eneity.Lack;
import cn.zust.se.eneity.Lacks;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface LackService {
    Integer insertLack(String uid,Date time,Integer buildingid, String dormitory,String intro);
    List<Lack> selAllLack();
    List<Lack> selLackByUid(String uid);
    List<Lack> selLackByBuilding(Integer buildingid);
    Integer delLack(Integer id);
    List<Lacks> selLacks(String name, Date time1, Date time2, Integer buildingid, String dormitory);
}
