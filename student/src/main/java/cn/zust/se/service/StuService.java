package cn.zust.se.service;

import cn.zust.se.eneity.Stu;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface StuService {
    Stu getStuById(Integer id);
    List<Stu> getsAll();
    Stu getStuByUid(Integer uid);
    Stu getStuByName(String name);
    int create(Stu stu);
    int delete(Integer id);


}
