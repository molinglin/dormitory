package cn.zust.se.dao;

import cn.zust.se.eneity.Distribution;
import cn.zust.se.eneity.EmptyBeds;
import cn.zust.se.eneity.Stu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DistributionDao {
    @Select("select stu.uid,stu.gender,stu.name,stu.college,stu.major,stu.classes,questionnaire.sleep,questionnaire.getup,questionnaire.temper from stu,questionnaire " +
            "where stu.status=0 and stu.uid=questionnaire.uid and stu.gender='男' " +
            "order by stu.college asc,stu.major asc,questionnaire.sleep asc,questionnaire.getup asc,stu.classes,questionnaire.temper")
    List<Distribution> selAllBD();
    @Select("select stu.uid,stu.gender,stu.name,stu.college,stu.major,stu.classes,questionnaire.sleep,questionnaire.getup,questionnaire.temper from stu,questionnaire " +
            "where stu.status=0 and stu.uid=questionnaire.uid and stu.gender='女' " +
            "order by stu.college asc,stu.major asc,questionnaire.sleep asc,questionnaire.getup asc,stu.classes,questionnaire.temper")
    List<Distribution> selAllGD();
    @Select("select bed.bid,building.type,bed.buildingid,bed.dormitory,bed.bednum from bed,building where building.id=bed.buildingid and bed.empty='Y' and building.type='男'")
    List<EmptyBeds> selEmptyBBeds();
    @Select("select bed.bid,building.type,bed.buildingid,bed.dormitory,bed.bednum from bed,building where building.id=bed.buildingid and bed.empty='Y' and building.type='女'")
    List<EmptyBeds> selEmptyGBeds();
    @Update("update bed set uid=#{uid},empty='N' where bid=#{bid}")
    Integer updateBed(@Param("uid") String uid,@Param("bid") String bid);
    @Update("update stu set buildingid=#{buildingid},dormitory=#{dormitory},bednum=#{bednum},status=1 where uid=#{uid}")
    Integer updateStu(@Param("uid") String uid,@Param("buildingid") String buildingid,@Param("dormitory") String dormitory,@Param("bednum") String bednum);
    @Select("select * from stu where gender='男'")
    List<Stu> selBStu();
    @Select("select * from stu where gender='女'")
    List<Stu> selGStu();

}
