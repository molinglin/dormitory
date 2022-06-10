package cn.zust.se.controller;

import cn.zust.se.eneity.Bed;
import cn.zust.se.service.BedService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "床位接口")
@RestController
public class BedController {
    @Autowired
    private BedService bedService;
    @GetMapping("/bed")
    public List<Bed> getallbed(){
        return bedService.getallbed();
    }
}
