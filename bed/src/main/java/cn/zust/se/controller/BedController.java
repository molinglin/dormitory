package cn.zust.se.controller;

import cn.zust.se.eneity.Bed;
import cn.zust.se.eneity.CommonResult;
import cn.zust.se.service.BedService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "bed接口")
@RestController
@RequestMapping( "/beds")
public class BedController {
    @Resource
    BedService bedService;

    @ApiOperation("查找所有空床位")
    @GetMapping("/selE")
    public CommonResult selE(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Bed> beds=bedService.selEmptyBeds();
        PageInfo<Bed> pageInfo=new PageInfo<>(beds);
        if(beds.size()!=0){
            return new CommonResult(200,"success",pageInfo.getList());
        }else {
            return new CommonResult (400,"fail",null);
        }
    }

    @ApiOperation("按楼宇查找空床位")
    @GetMapping("/selEByBuild")
    public CommonResult selEByBuild(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,Integer buildingid){
        PageHelper.startPage(pageNum,5);
        List<Bed> beds=bedService.selEmptyBedsByBuilding(buildingid);
        PageInfo<Bed> pageInfo=new PageInfo<>(beds);
        if(beds.size()!=0){
            return new CommonResult(200,"success",pageInfo.getList());
        }else {
            return new CommonResult (400,"fail",null);
        }
    }

    @ApiOperation("按楼层查找空床位")
    @GetMapping("/selEByFloor")
    public CommonResult selEByFloor(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,String floor){
        PageHelper.startPage(pageNum,5);
        List<Bed> beds=bedService.selEmptyBedsByFloor(floor);
        PageInfo<Bed> pageInfo=new PageInfo<>(beds);
        if(beds.size()!=0){
            return new CommonResult(200,"success",pageInfo.getList());
        }else {
            return new CommonResult (400,"fail",null);
        }
    }

    @ApiOperation("按楼宇和楼层查找所有空床位")
    @GetMapping("/selEByBAndF")
    public CommonResult selEByBAndF(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,Integer buildingid,String floor){
        PageHelper.startPage(pageNum,5);
        List<Bed> beds=bedService.selEmptyBedsByBAndF(buildingid,floor);
        PageInfo<Bed> pageInfo=new PageInfo<>(beds);
        if(beds.size()!=0){
            return new CommonResult(200,"success",pageInfo.getList());
        }else {
            return new CommonResult (400,"fail",null);
        }
    }

    @ApiOperation("查找所有床位")
    @GetMapping("/selAll")
    public CommonResult selAllBeds(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Bed> beds=bedService.selAllBeds();
        PageInfo<Bed> pageInfo=new PageInfo<>(beds);
        if(beds.size()!=0){
            return new CommonResult(200,"success",pageInfo.getList());
        }else {
            return new CommonResult (400,"fail",null);
        }
    }

    @ApiOperation("按楼宇查找所有床位")
    @GetMapping("/selAllByBuild")
    public CommonResult selAllByBuild(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,Integer buildingid){
        PageHelper.startPage(pageNum,5);
        List<Bed> beds=bedService.selBedsByBuilding(buildingid);
        PageInfo<Bed> pageInfo=new PageInfo<>(beds);
        if(beds.size()!=0){
            return new CommonResult(200,"success",pageInfo.getList());
        }else {
            return new CommonResult (400,"fail",null);
        }
    }

    @ApiOperation("按楼层查找所有床位")
    @GetMapping("/selAllByFloor")
    public CommonResult selAllByFloor(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,String floor){
        PageHelper.startPage(pageNum,5);
        List<Bed> beds=bedService.selBedsByFloor(floor);
        PageInfo<Bed> pageInfo=new PageInfo<>(beds);
        if(beds.size()!=0){
            return new CommonResult(200,"success",pageInfo.getList());
        }else {
            return new CommonResult (400,"fail",null);
        }
    }

    @ApiOperation("按楼宇和楼层查找所有床位")
    @GetMapping("/selAllByBAndF")
    public CommonResult selAllByBAndF(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,Integer buildingid,String floor){
        PageHelper.startPage(pageNum,1);
        List<Bed> beds=bedService.selBedsByBAndF(buildingid,floor);
        PageInfo<Bed> pageInfo=new PageInfo<>(beds);
        if(beds.size()!=0){
            return new CommonResult(200,"success",pageInfo.getList());
        }else {
            return new CommonResult (400,"fail",null);
        }
    }
}