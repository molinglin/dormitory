package cn.zust.se.service;

import cn.zust.se.eneity.Hygiene;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface HygieneService {
    List<Hygiene> selAllHy();
    List<Hygiene> selHyByBuilding(Integer buildingid);
    List<Hygiene> selHyByTimes(Integer times);
    List<Hygiene> selHyByBAndT(Integer buildingid,Integer times);
    List<Hygiene> selHyByBAndD(Integer buildingid,String dormitory);
    List<Hygiene> selHyByBAndTAndD(Integer buildingid,Integer times,String dormitory);
    Integer insertHy(Integer times,Integer buildingid,String dormitory,Integer result);
    public Map<String,Object> insertHyByExcel(MultipartFile file);
    List<Hygiene> selHygiene(Integer times,Integer buildingid,String dormitory,Integer result1,Integer result2);
    Integer updateHygiene(Integer result,Integer id);

}
