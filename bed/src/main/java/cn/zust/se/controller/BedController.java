package cn.zust.se.controller;

import cn.zust.se.eneity.CommonResult;
import cn.zust.se.service.BedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.C;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "bed接口")
@RestController
@RequestMapping( "/beds")
public class BedController {
    @Resource
    BedService bedService;

    @ApiOperation("查找所有空床位")
    @GetMapping("/sleE")
    public CommonResult selE(){
        return new CommonResult (100,"success",bedService.selEmptyBeds());
    }

    @ApiOperation("按楼宇查找空床位")
    @GetMapping("/selEByBuild")
    public CommonResult selEByBuild(Integer buildingid){
        return new CommonResult(100,"success",bedService.selEmptyBedsByBuilding(buildingid));
    }

    @ApiOperation("按楼层查找空床位")
    @GetMapping("/selEByFloor")
    public CommonResult selEByFloor(String floor){
        return new CommonResult(100,"success",bedService.selEmptyBedsByFloor(floor));
    }
}
