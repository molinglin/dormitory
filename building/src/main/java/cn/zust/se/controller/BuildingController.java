package cn.zust.se.controller;

import cn.zust.se.eneity.Building;
import cn.zust.se.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BuildingController {
    @Autowired
    private BuildingService buildingService;
    @GetMapping("/he")
    public List<Building> he(){
        return buildingService.getallbuilding();
    }
}
