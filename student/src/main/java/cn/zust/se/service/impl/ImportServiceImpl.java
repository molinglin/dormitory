package cn.zust.se.service.impl;

import cn.zust.se.dao.StuDao;
import cn.zust.se.eneity.Stu;
import cn.zust.se.service.ImportService;
import cn.zust.se.util.ImportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;
@Service
public class ImportServiceImpl implements ImportService {
    @Autowired
    StuDao stuDao;

    @Override
    public Map<String, Object> importStu(MultipartFile file) {
        Map<String, Object> resultMap = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Stu> students = new ArrayList<>();

        try {

            //验证文件类型
            if (!file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).equals(".xls") && !file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).equals(".xlsx")) {
                resultMap.put("mete", "文件类型有误！请上传Excle文件");
                throw new Exception("文件类型有误！请上传Excle文件");
            }

            //获取数据
            List<List<Object>> olist = ImportExcelUtil.getListByExcel(file.getInputStream(), file.getOriginalFilename());

            resultMap.put("导入成功", 200);
            //封装数据
            for (int i = 0; i < olist.size(); i++) {
                List<Object> list = olist.get(i);
                if (list.get(0) == "" || ("序号").equals(list.get(0))) {
                    continue;
                }
               Stu stu=new Stu();
                //根据下标获取每一行的每一条数据
                if (String.valueOf(list.get(0)) == null || String.valueOf(list.get(0)).equals("")) {
                    resultMap.put("mete", "学号不能为空");
                    throw new Exception("学号不能为空");
                }

                stu.setUid(String.valueOf(list.get(0)));

                if (String.valueOf(list.get(1)) == null || String.valueOf(list.get(1)).equals("")) {
                    resultMap.put("mete", "姓名不能为空");
                    throw new Exception("姓名不能为空");
                }
                stu.setName(String.valueOf(list.get(1)));

                if (String.valueOf(list.get(2)) == null || String.valueOf(list.get(2)).equals("")) {
                    resultMap.put("mete", "性别不能为空");
                    throw new Exception("性别不能为空");
                }
                stu.setGender(String.valueOf(list.get(2)));

                if (String.valueOf(list.get(3)) == null || String.valueOf(list.get(3)).equals("")) {
                    resultMap.put("mete", "导入失败,电话不能为空");
                    throw new Exception("导入失败,电话能为空");
                }
                stu.setPhone(String.valueOf(list.get(3)));


                if (String.valueOf(list.get(4)) == null || String.valueOf(list.get(4)).equals("")) {
                    resultMap.put("mete", "导入失败,学院不能为空");
                    throw new Exception("导入失败,学院不能为空");
                }
                stu.setCollege(String.valueOf(list.get(4)));

                if (String.valueOf(list.get(5)) == null || String.valueOf(list.get(5)).equals("")) {
                    resultMap.put("mete", "专业不能为空");
                    throw new Exception("导入失败,专业不能为空");
                }
                stu.setMajor(String.valueOf(list.get(5)));

                if (String.valueOf(list.get(6)) == null || String.valueOf(list.get(6)).equals("")) {
                    resultMap.put("mete", "班级不能为空");
                    throw new Exception("导入失败,班级不能为空");
                }
               stu.setClasses(String.valueOf(list.get(6)));
                stu.setDormitory(null);
                stu.setBuildingid(null);
                stu.setBednum(null);
                stu.setStatus(0);
                students.add(stu);
            }


            int i = stuDao.insertList(students);
            if (i != 0) {
                resultMap.put("state", 200);
            } else {
                resultMap.put("mete", "文档内无数据，请重新导入");
                resultMap.put("state", 500);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            return resultMap;
        }
    }
}
