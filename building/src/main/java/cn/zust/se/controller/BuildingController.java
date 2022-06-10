package cn.zust.se.controller;

import cn.zust.se.eneity.Building;
import cn.zust.se.eneity.CommonResult;
import cn.zust.se.service.BuildingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Api(value = "楼宇接口")
@RestController
@RequestMapping("/building")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;
    @ApiOperation(value = "获取所有楼宇信息")
    @GetMapping ("/show")
    public CommonResult<List> show(){
        List<Building> builds = buildingService.getallbuilding();
        if(builds!=null){
            return new CommonResult<List>(200,"成功",builds);
        }else {
            return new CommonResult<List>(400,"失败",null);
        }
    }
}
