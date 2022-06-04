package cn.zust.se.service;

import cn.zust.se.eneity.Stu;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StuService {
    Stu getStuById(Integer id);
    List<Stu> getsAll();
    Stu getStuByUid(String uid);
    List<Stu> getStuByName(String name);
    int create(Stu stu);
    int delete(Integer id);
    int updateDormitory( Integer id,  String dormitory);//修改寝室
    int updateBuild( Integer id,  Integer buildingid);//修改建筑
    int updateBed(Integer id,Integer bednum);
    List<Stu> getStudByDormitory(String dormitory);
}
