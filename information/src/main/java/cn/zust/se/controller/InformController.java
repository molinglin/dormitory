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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Api(value = "信息查看接口")
@RestController
@RequestMapping("/inform")
public class InformController {
    @Resource
    InformService informService;

    @ApiOperation("添加通知")
    @PostMapping("/insertInform")
    public CommonResult insertInform(@RequestBody Inform inform){
//        Date date=new Date();
//        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
//        String time=dateFormat.format(date);
        if(informService.insertInform(inform)==1){
            return new CommonResult<>(200,"success",null);
        }else {
            return new CommonResult<>(400,"fail",null);
        }
    }

    @ApiOperation("修改通知")
    @PostMapping("/updateInform")
    public CommonResult updateInform(@Param("id") Integer id,@Param("title") String title,@Param("content") String content,@Param("user") String user){
        System.out.println(id);
        System.out.println(user);
        if(informService.selInform(id)==null || !Objects.equals(informService.selInform(id).getPublisher(), user)){
            return new CommonResult<>(400,"不能修改其他人发布的通知",null);
//            informService.updateInform(id, title, content,user);
//            return new CommonResult<>(200,"success",null);
        }else {
            informService.updateInform(id, title, content);
            return new CommonResult<>(200,"success",null);
        }
    }

    @ApiOperation("删除通知")
    @DeleteMapping("/delInform")
    public CommonResult delInform(Integer id,String user){
        if(informService.selInform(id)==null || !Objects.equals(informService.selInform(id).getPublisher(), user)){
            return new CommonResult(400,"fail");
        }else {
            informService.delInform(id);
            return new CommonResult<>(200,"del success",null);
        }
    }

    @ApiOperation("查询所有通知")
    @GetMapping("/selAllInforms")
    public CommonResult selAllInforms(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
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
    public CommonResult selInformsByP(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize,String publisher){
        PageHelper.startPage(pageNum,pageSize);
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
    public CommonResult selInformsByT(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize,Date time){
        PageHelper.startPage(pageNum,pageSize);
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
    public CommonResult selInformsByPAndT(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize,String publisher,Date time){
        PageHelper.startPage(pageNum,pageSize);
        List<Inform> informs=informService.selInformByPAndT(publisher,time);
        PageInfo<Inform> pageInfo=new PageInfo<>(informs);
        if(informs.isEmpty()){
            return new CommonResult(400,"fail",null);
        }else {
            return new CommonResult<>(200,"success",pageInfo.getList());
        }
    }

    @ApiOperation("通知联合查询")
    @GetMapping("/selInforms")
    public CommonResult selInforms(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize,String publisher,Date time){
        if(publisher=="")publisher=null;
        PageHelper.startPage(pageNum,pageSize);
        List<Inform> informs=informService.selInforms(publisher,time);
        PageInfo<Inform> pageInfo=new PageInfo<>(informs);
        if(informs.isEmpty()){
            return new CommonResult(400,"fail",null);
        }else {
            return new CommonResult<>(200,"success",pageInfo);
        }
    }
}
