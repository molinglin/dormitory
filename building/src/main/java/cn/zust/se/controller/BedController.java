package cn.zust.se.controller;

import cn.zust.se.eneity.Bed;
import cn.zust.se.eneity.Building;
import cn.zust.se.eneity.CommonResult;
import cn.zust.se.service.BedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "床位接口")
@RestController
@RequestMapping("/bed")
public class BedController {
    @Autowired
    private BedService bedService;
    @ApiOperation(value = "获取所有床位信息")
    @GetMapping ("/show")
    public CommonResult<List> show(){
        List<Bed> beds = bedService.getallbed();
        if(beds!=null){
            return new CommonResult<List>(200,"成功",beds);
        }else {
            return new CommonResult<List>(400,"失败",null);
        }
    }
}
