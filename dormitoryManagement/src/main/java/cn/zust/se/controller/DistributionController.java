package cn.zust.se.controller;

import cn.zust.se.eneity.*;
import cn.zust.se.service.DistributionService;
import cn.zust.se.service.StuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static cn.zust.se.util.StartPage.startPage;

@Api("宿舍分配")
@RestController
@RequestMapping("/distribution")
public class DistributionController {
    @Resource
    DistributionService distributionService;
//    @Resource
//    StuService stuService;

    @ApiOperation("一键为男生分配寝室")
    @PostMapping("/dForBs")
    public CommonResultBeds dForBs(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum, @RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize){
        List<Distribution> distributions=distributionService.distributionB();
        if(distributions!=null){
            updateBAndS(distributions);
//            List<Distribution> distributions1=startPage(distributions,pageNum,pageSize);
            List<Stu> stus=distributionService.selBStu();
            List<Stu> stus1=startPage(stus,pageNum,pageSize);
            return new CommonResultBeds<>(200,"success",stus.size(),stus1);
        }else return new CommonResultBeds<>(400,"空余宿舍不足",null);
    }

    @ApiOperation("一键为女生分配寝室")
    @PostMapping("/dForGs")
    public CommonResultBeds dForGs(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum, @RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize){
        List<Distribution> distributions=distributionService.distributionG();
        if(distributions!=null){
            updateBAndS(distributions);
//            List<Distribution> distributions1=startPage(distributions,pageNum,pageSize);
            List<Stu> stus=distributionService.selGStu();
            List<Stu> stus1=startPage(stus,pageNum,pageSize);
            return new CommonResultBeds<>(200,"success",stus.size(),stus1);
        }else return new CommonResultBeds<>(400,"空余宿舍不足",null);
    }

    private void updateBAndS(List<Distribution> distributions) {
        for (int i=0;i<distributions.size();i++){
            String uid=distributions.get(i).getUid();
            String bid=distributions.get(i).getBid();
            String buildingid=distributions.get(i).getBuildingid();
            String dormitory=distributions.get(i).getDormitory();
            String bednum=distributions.get(i).getBednum();
            Integer j=distributionService.updateBed(uid,bid);
            Integer k=distributionService.updateStu(uid,buildingid,dormitory,bednum);
//                stus.add(distributionService.selStu(uid));
        }
    }
}
