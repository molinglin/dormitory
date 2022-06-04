package cn.zust.se.service.impl;

import cn.zust.se.dao.StuDao;
import cn.zust.se.eneity.Stu;
import cn.zust.se.service.StuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Stu getStuByUid(String uid) {
        return stuDao.getStuByUid(uid);
    }

    @Override
    public List<Stu> getStuByName(String name) {
        return stuDao.getStuByName(name);
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

    @Override
    public List<Stu> getStudByDormitory(String dormitory) {
        return stuDao.getStudByDormitory(dormitory);
    }


}
