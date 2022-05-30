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
    public Stu getStuByUid(Integer uid) {
        return stuDao.getStuByUid(uid);
    }

    @Override
    public Stu getStuByName(String name) {
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


}
