package cn.zust.se.controller;

import cn.zust.se.eneity.CommonResult;
import cn.zust.se.eneity.Lack;
import cn.zust.se.eneity.Lacks;
import cn.zust.se.service.LackService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Api("缺寝管理接口")
@RestController
@RequestMapping("/lack")
public class LackController {
    @Resource
    LackService lackService;
    @ApiOperation("添加缺寝记录")
    @PostMapping("/insertLack")
    public CommonResult insertLack(String uid, Date time, Integer buildingid, String dormitory,String intro){
        if(lackService.insertLack(uid, time, buildingid, dormitory,intro)==1){
            return new CommonResult<>(200,"success",null);
        }else return new CommonResult<>(400,"fail",null);
    }

    @ApiOperation("按时间次序查找所有记录")
    @GetMapping("/selAllLack")
    public CommonResult selAllLack(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Lack> lacks=lackService.selAllLack();
        PageInfo<Lack> pageInfo=new PageInfo<>(lacks);
        if(lacks.isEmpty()){
            return new CommonResult(400,"fail",null);
        }else {
            return new CommonResult<>(200,"success",pageInfo.getList());
        }
    }

    @ApiOperation("按时间次序与学号查找所有记录")
    @GetMapping("/selLackByUid")
    public CommonResult selLackByUid(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize,String uid){
        PageHelper.startPage(pageNum,pageSize);
        List<Lack> lacks=lackService.selLackByUid(uid);
        PageInfo<Lack> pageInfo=new PageInfo<>(lacks);
        if(lacks.isEmpty()){
            return new CommonResult(400,"fail",null);
        }else {
            return new CommonResult<>(200,"success",pageInfo.getList());
        }
    }

    @ApiOperation("按时间次序与楼宇查找所有记录")
    @GetMapping("/selLackByB")
    public CommonResult selLackByB(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize,Integer buildingid){
        PageHelper.startPage(pageNum,pageSize);
        List<Lack> lacks=lackService.selLackByBuilding(buildingid);
        PageInfo<Lack> pageInfo=new PageInfo<>(lacks);
        if(lacks.isEmpty()){
            return new CommonResult(400,"fail",null);
        }else {
            return new CommonResult<>(200,"success",pageInfo.getList());
        }
    }

    @ApiOperation("删除缺寝记录")
    @DeleteMapping("/delLack")
    public CommonResult delLack(Integer id,String user){
        if(Objects.equals(user, "admin")){
            lackService.delLack(id);
            return new CommonResult<>(200,"success");
        }else if(Objects.equals(lackService.selB(lackService.selL(id).getBuildingid()).getMasterid(), user)){
            lackService.delLack(id);
            return new CommonResult<>(200,"success");
        }else {
            return new CommonResult<>(400,"你没有权限删除此记录");
        }
//        if(lackService.delLack(id) == 1){
//            return new CommonResult<>(200,"success");
//        }else {
//            return new CommonResult<>(400,"fail");
//        }
    }

    @ApiOperation("联合查询缺寝记录")
    @GetMapping("/selLacks")
    public CommonResult selLacks(String name,@RequestParam(defaultValue = "2000/1/1",value = "time1") Date time1,@RequestParam(defaultValue = "2100/1/1",value = "time2") Date time2, Integer buildingid, String dormitory,@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize){
        if(name=="")name=null;
        if(dormitory=="")dormitory=null;
        PageHelper.startPage(pageNum,pageSize);
        List<Lacks> lacks=lackService.selLacks(name, time1, time2, buildingid, dormitory);
//        Integer count=lackService.selLacks(name, time1, time2, buildingid, dormitory).size();
//        System.out.println(count);
        PageInfo<Lacks> pageInfo=new PageInfo<>(lacks);
        if(lacks.isEmpty()){
            return new CommonResult(400,"fail",null);
        }else {
            return new CommonResult<>(200,"success",pageInfo);
        }
//    return new CommonResult<>(200,"success",lackService.selLacks(name, time1, time2, buildingid, dormitory));
    }
}
