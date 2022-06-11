package cn.zust.se.service.impl;

import cn.zust.se.dao.StuDao;
import cn.zust.se.eneity.Stu;
import cn.zust.se.service.StuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    StuDao stuDao;

    @Override
    public Stu getStuById(Integer id) {
        return stuDao.getStuById(id);
    }

    @Override
    public List<Stu> getsAll() {
        return stuDao.getsAll();
    }

    @Override
    public List<Stu> getStuByUid(String uid) {
        return stuDao.getStuByUid(uid);
    }

    @Override
    public List<Stu> getStuByName(String name) {
        return stuDao.getStuByName(name);
    }

    @Override
    public List<Stu> getStudnan() {
        return stuDao.getStudnan();
    }

    @Override
    public List<Stu> getStudnv() {
        return stuDao.getStudnv();
    }

    @Override
    public int create(Stu stu) {
        return stuDao.create(stu);
    }

    @Override
    public int delete(Integer id) {
        return stuDao.delete(id);
    }

    @Override
    public int updateDormitory(Integer id, String dormitory) {
        return stuDao.updateDormitory(id,dormitory);
    }

    @Override
    public int updateBuild(Integer id, Integer buildingid) {
        return stuDao.updateBuildingId(id,buildingid);
    }

    @Override
    public int updateBed(Integer id, Integer bednum) {
        return stuDao.updateBedNum(id,bednum);
    }

//    @Override
//    public int update(Stu stu) {
//        if(stu.getBuildingid()==null && stu.getDormitory()==null && stu.getBednum()==null){
//            stu.setStatus(0);
//        }else if(stu.getBuildingid()!=null && stu.getDormitory()!=null && stu.getBednum()!=null){
//            stu.setStatus(1);
//        }else {
//            return 0;
//        }
//        return stuDao.update(stu);
//    }


    @Override
    public int update(String uid, String name, String gender, String phone, String college, String major, String classes, String dormitory, Integer buildingid, Integer bednum) {
        if(dormitory=="") dormitory=null;
        if(buildingid==null && dormitory==null && bednum==null){
            Integer status=0;
            return stuDao.update2(uid,name,gender,phone,college,major,classes,status);
        }else if(buildingid!=null && dormitory!=null && bednum!=null){
            Integer status=1;
            String bid= String.valueOf(buildingid)+dormitory+String.valueOf(bednum);
            if(Objects.equals(stuDao.selBed2(bid).getEmpty(), "Y")){
                stuDao.updateBed2(bid,uid);
            }else {
                return 0;
            }
            return stuDao.update(uid,name,gender,phone,college,major,classes,dormitory,buildingid,bednum,status);
        }else {
            return 0;
        }
    }

    @Override
    public List<Stu> getStudByDormitory(String dormitory) {
        return stuDao.getStudByDormitory(dormitory);
    }

    @Override
    public List<Stu> getStuByUandN(String uid, String name) {
        return stuDao.getStuByUandN(uid,name);
    }

    @Override
    public List<Stu> getStuByUandD(String uid, String dormitory) {
        return stuDao.getStuByUandD(uid,dormitory);
    }

    @Override
    public List<Stu> getStuByNandD(String name, String dormitory) {
        return stuDao.getStuByNandD(name,dormitory);
    }

    @Override
    public List<Stu> getStuByAll(String uid, String name, String dormitory) {
        return stuDao.getStuByAll(uid,name,dormitory);
    }


}
