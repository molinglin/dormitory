package cn.zust.se.service.impl;

import cn.zust.se.dao.HygieneDao;
import cn.zust.se.eneity.Hygiene;
import cn.zust.se.eneity.Stu;
import cn.zust.se.service.HygieneService;
import cn.zust.se.util.ImportExcelUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HygieneServiceImpl implements HygieneService {
    @Resource
    HygieneDao hygieneDao;

    @Override
    public List<Hygiene> selAllHy() {
        return hygieneDao.selAllHy();
    }

    @Override
    public List<Hygiene> selHyByBuilding(Integer buildingid) {
        return hygieneDao.selHyByBuilding(buildingid);
    }

    @Override
    public List<Hygiene> selHyByTimes(Integer times) {
        return hygieneDao.selHyByTimes(times);
    }

    @Override
    public List<Hygiene> selHyByBAndT(Integer buildingid, Integer times) {
        return hygieneDao.selHyByBAndT(buildingid, times);
    }

    @Override
    public List<Hygiene> selHyByBAndD(Integer buildingid, String dormitory) {
        return hygieneDao.selHyByBAndD(buildingid, dormitory);
    }

    @Override
    public List<Hygiene> selHyByBAndTAndD(Integer buildingid, Integer times, String dormitory) {
        return hygieneDao.selHyByBAndTAndD(buildingid, times, dormitory);
    }

    @Override
    public Integer insertHy(Integer times, Integer buildingid, String dormitory, Integer result) {
        return hygieneDao.insertHy(times, buildingid, dormitory, result);
    }

    @Override
    public List<Hygiene> selHygiene(Integer times, Integer buildingid, String dormitory, Integer result1, Integer result2) {
        return hygieneDao.selHygiene(times, buildingid, dormitory, result1, result2);
    }

    @Override
    public Integer updateHygiene(Integer result, Integer id) {
        return hygieneDao.updateHygiene(result, id);
    }

    @Override
    public Map<String, Object> insertHyByExcel(MultipartFile file) {
        Map<String,Object> resultMap=new HashMap<>();
        List<Hygiene> hygienes = new ArrayList<>();
        try{
            if (!file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).equals(".xls") && !file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).equals(".xlsx")) {
                resultMap.put("mete", "??????????????????????????????Excel??????");
                throw new Exception("??????????????????????????????Excel??????");
            }
            List<List<Object>> olist = ImportExcelUtil.getListByExcel(file.getInputStream(), file.getOriginalFilename());
            resultMap.put("????????????", 200);
            for (int i = 0; i < olist.size(); i++){
                List<Object> list = olist.get(i);
                if (list.get(0) == "" || ("??????").equals(list.get(0))) {
                    continue;
                }
                Hygiene hygiene=new Hygiene();

                if (String.valueOf(list.get(0)) == null || String.valueOf(list.get(0)).equals("")) {
                    resultMap.put("mete", "??????????????????");
                    throw new Exception("??????????????????");
                }
                hygiene.setTimes(Integer.parseInt(String.valueOf(list.get(0))));

                if (String.valueOf(list.get(1)) == null || String.valueOf(list.get(1)).equals("")) {
                    resultMap.put("mete", "?????????????????????");
                    throw new Exception("?????????????????????");
                }
                hygiene.setBuildingid(Integer.parseInt(String.valueOf(list.get(1))));

                if (String.valueOf(list.get(2)) == null || String.valueOf(list.get(2)).equals("")) {
                    resultMap.put("mete", "?????????????????????");
                    throw new Exception("?????????????????????");
                }
                hygiene.setDormitory(String.valueOf(list.get(2)));

                if (String.valueOf(list.get(3)) == null || String.valueOf(list.get(3)).equals("")) {
                    resultMap.put("mete", "??????????????????");
                    throw new Exception("??????????????????");
                }
                hygiene.setResult((Integer.parseInt(String.valueOf(list.get(3)))));
                hygienes.add(hygiene);
            }
            Integer j=hygieneDao.insertHyByExcel(hygienes);
            if (j != 0) {
                resultMap.put("state", 200);
            } else {
                resultMap.put("mete", "???????????????????????????????????????");
                resultMap.put("state", 500);
            }
        }catch (Exception e) {
            e.printStackTrace();

        } finally {
            return resultMap;
        }
    }
}
