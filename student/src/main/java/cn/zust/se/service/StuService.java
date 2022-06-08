package cn.zust.se.service;

import cn.zust.se.eneity.Stu;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StuService {
    Stu getStuById(Integer id);
    List<Stu> getsAll();
    Stu getStuByUid(String uid);
    List<Stu> getStuByName(String name);
    List<Stu> getStudnan();
    List<Stu> getStudnv();
    int create(Stu stu);
    int delete(Integer id);
    int updateDormitory( Integer id,  String dormitory);//修改寝室
    int updateBuild( Integer id,  Integer buildingid);//修改建筑
    int updateBed(Integer id,Integer bednum);
    int update(Stu stu);
    List<Stu> getStudByDormitory(String dormitory);
}
