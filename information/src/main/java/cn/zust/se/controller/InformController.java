package cn.zust.se.controller;

import cn.zust.se.eneity.CommonResult;
import cn.zust.se.service.InformService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@Api(value = "信息查看接口")
@RestController
@RequestMapping("/inform")
public class InformController {
    @Resource
    InformService informService;

    @ApiOperation("添加通知")
    @PostMapping("/insertInform")
    public CommonResult insertInform(@Param("time") Date time,@Param("publisher") String publisher,@Param("content") String content){
        if(informService.insertInform(time, publisher, content)==1){
            return new CommonResult<>(200,"success",null);
        }else {
            return new CommonResult<>(400,"fail",null);
        }
    }
}
