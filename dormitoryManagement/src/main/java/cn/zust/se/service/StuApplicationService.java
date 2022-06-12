package cn.zust.se.service;

import cn.zust.se.eneity.StuApplication;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StuApplicationService {
    int insert(String uid1,String uid2,Integer applicationid, Integer access);
    List<StuApplication> findNoAccess();
    List<StuApplication> findAccess();
    List<StuApplication> findByUid2(Integer uid);
    List<StuApplication> findByUid1(Integer uid);
    int agree(Integer id);
    int reject(Integer id);
    StuApplication findById(Integer id);
}
