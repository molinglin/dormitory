package cn.zust.se.controller;

import cn.zust.se.eneity.CommonResult;
import cn.zust.se.eneity.Inform;
import cn.zust.se.service.InformService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    @ApiOperation("查询所有通知")
    @GetMapping("/selInforms")
    public CommonResult selInforms(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Inform> informs=informService.selAllInform();
        PageInfo<Inform> pageInfo=new PageInfo<>(informs);
        if(informs.isEmpty()){
            return new CommonResult(400,"fail",null);
        }else {
            return new CommonResult<>(200,"success",pageInfo.getList());
        }
    }

    @ApiOperation("查询某人发布的通知")
    @GetMapping("/selInformsByP")
    public CommonResult selInformsByP(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,String publisher){
        PageHelper.startPage(pageNum,5);
        List<Inform> informs=informService.selInformByPublisher(publisher);
        PageInfo<Inform> pageInfo=new PageInfo<>(informs);
        if(informs.isEmpty()){
            return new CommonResult(400,"fail",null);
        }else {
            return new CommonResult<>(200,"success",pageInfo.getList());
        }
    }

    @ApiOperation("查询某个时间后的通知")
    @GetMapping("/selInformsByT")
    public CommonResult selInformsByT(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,Date time){
        PageHelper.startPage(pageNum,5);
        List<Inform> informs=informService.selInformByTime(time);
        PageInfo<Inform> pageInfo=new PageInfo<>(informs);
        if(informs.isEmpty()){
            return new CommonResult(400,"fail",null);
        }else {
            return new CommonResult<>(200,"success",pageInfo.getList());
        }
    }

    @ApiOperation("查询某人某个时间后发布的通知")
    @GetMapping("/selInformsByPAndT")
    public CommonResult selInformsByPAndT(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,String publisher,Date time){
        PageHelper.startPage(pageNum,5);
        List<Inform> informs=informService.selInformByPAndT(publisher,time);
        PageInfo<Inform> pageInfo=new PageInfo<>(informs);
        if(informs.isEmpty()){
            return new CommonResult(400,"fail",null);
        }else {
            return new CommonResult<>(200,"success",pageInfo.getList());
        }
    }
}
