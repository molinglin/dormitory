package cn.zust.se.controller;

import cn.zust.se.eneity.CommonResult;
import cn.zust.se.eneity.Hygiene;
import cn.zust.se.eneity.Inform;
import cn.zust.se.service.HygieneService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "卫生信息操作接口")
@RestController
@RequestMapping("/hygiene")
public class HygieneController {
    @Resource
    HygieneService hygieneService;

    @ApiOperation("查找所有卫生信息")
    @GetMapping("/selAllHy")
    public CommonResult selAllHy(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Hygiene> hygiene=hygieneService.selAllHy();
        PageInfo<Hygiene> pageInfo=new PageInfo<>(hygiene);
        if(hygiene.isEmpty()){
            return new CommonResult(400,"fail",null);
        }else {
            return new CommonResult<>(200,"success",pageInfo.getList());
        }
    }

    @ApiOperation("按楼宇查找卫生信息")
    @GetMapping("/selHyByBuilding")
    public CommonResult selHyByBuilding(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,Integer buildingid){
        PageHelper.startPage(pageNum,5);
        List<Hygiene> hygiene=hygieneService.selHyByBuilding(buildingid);
        PageInfo<Hygiene> pageInfo=new PageInfo<>(hygiene);
        if(hygiene.isEmpty()){
            return new CommonResult(400,"fail",null);
        }else {
            return new CommonResult<>(200,"success",pageInfo.getList());
        }
    }

    @ApiOperation("按时间查找卫生信息")
    @GetMapping("/selHyByTimes")
    public CommonResult selHyByTimes(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,Integer times){
        PageHelper.startPage(pageNum,5);
        List<Hygiene> hygiene=hygieneService.selHyByTimes(times);
        PageInfo<Hygiene> pageInfo=new PageInfo<>(hygiene);
        if(hygiene.isEmpty()){
            return new CommonResult(400,"fail",null);
        }else {
            return new CommonResult<>(200,"success",pageInfo.getList());
        }
    }

    @ApiOperation("按楼宇与时间查找卫生信息")
    @GetMapping("/selHyByBAndT")
    public CommonResult selHyByBAndT(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,Integer buildingid,Integer times){
        PageHelper.startPage(pageNum,5);
        List<Hygiene> hygiene=hygieneService.selHyByBAndT(buildingid, times);
        PageInfo<Hygiene> pageInfo=new PageInfo<>(hygiene);
        if(hygiene.isEmpty()){
            return new CommonResult(400,"fail",null);
        }else {
            return new CommonResult<>(200,"success",pageInfo.getList());
        }
    }

    @ApiOperation("按楼宇与寝室查找卫生信息")
    @GetMapping("/selHyByBAndD")
    public CommonResult selHyByBAndT(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,Integer buildingid,String dormitory){
        PageHelper.startPage(pageNum,5);
        List<Hygiene> hygiene=hygieneService.selHyByBAndD(buildingid, dormitory);
        PageInfo<Hygiene> pageInfo=new PageInfo<>(hygiene);
        if(hygiene.isEmpty()){
            return new CommonResult(400,"fail",null);
        }else {
            return new CommonResult<>(200,"success",pageInfo.getList());
        }
    }

    @ApiOperation("按楼宇时间寝室查找卫生信息")
    @GetMapping("/selHyByBAndTAndD")
    public CommonResult selHyByBAndTAndD(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,Integer buildingid,Integer times,String dormitory){
        PageHelper.startPage(pageNum,5);
        List<Hygiene> hygiene=hygieneService.selHyByBAndTAndD(buildingid, times, dormitory);
        PageInfo<Hygiene> pageInfo=new PageInfo<>(hygiene);
        if(hygiene.isEmpty()){
            return new CommonResult(400,"fail",null);
        }else {
            return new CommonResult<>(200,"success",pageInfo.getList());
        }
    }

    @ApiOperation("添加成绩")
    @PostMapping("/insertHy")
    public CommonResult insertHy(Integer times,Integer buildingid,String dormitory,Integer result){
        if (hygieneService.insertHy(times, buildingid, dormitory, result)==1){
            return new CommonResult<>(200,"success",null);
        }else {
            return new CommonResult(400,"fail",null);
        }
    }

    @ApiOperation("批量添加")
    @PostMapping("/insertHyByExcel")
    public CommonResult insertHyByExcel(@RequestParam("file")MultipartFile file){
        Integer state= (Integer) hygieneService.insertHyByExcel(file).get("state");
        if(state.equals(200)){
            return new CommonResult<>(200,"添加成功",state);
        }else {
            return new CommonResult<>(500,"添加失败",null);
        }
    }
}