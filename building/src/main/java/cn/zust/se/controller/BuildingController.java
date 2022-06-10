package cn.zust.se.controller;

import cn.zust.se.eneity.Building;
import cn.zust.se.service.BuildingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Api(value = "楼宇接口")
@RestController
public class BuildingController {
    @Autowired
    private BuildingService buildingService;
    @GetMapping("/building")
    public List<Building> getallbuilding(){
        return buildingService.getallbuilding();
    }
}
