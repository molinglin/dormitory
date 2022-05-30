package cn.zust.se.controller;

import cn.zust.se.eneity.CommonResult;
import cn.zust.se.eneity.Stu;
import cn.zust.se.service.StuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "学生controller",tags ={"学生接口"})
@RestController
public class StuController {
    @Autowired
    StuService stuService;

    @ApiOperation(value = "获取学生信息")
    @GetMapping("/students/{id}")
    public CommonResult<Stu> getStuById(@ApiParam("id") @PathVariable("id") Integer id){
        Stu stu = stuService.getStuById(id);
        if(stu!=null){
            return new CommonResult<Stu>(200,"查询成功",stu);
        }else {
            return new CommonResult<Stu>(400,"查找失败",null);
        }
    }
//    @ApiOperation(value = "获取所有学生信息")
//    @PostMapping("/show")
//    public CommonResult<List> show(){
//        List<Stu> stus = stuService.getsAll();
//        if(stus!=null){
//            return new CommonResult<List>(200,"成功",stus);
//        }else {
//            return new CommonResult<List>(400,"失败",null);
//        }
//    }
    @ApiOperation(value = "分页")
    @GetMapping("/students")
    public CommonResult pageshow(@ApiParam(value = "pageNum") @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Stu> stus = stuService.getsAll();
        PageInfo<Stu> pageInfo=new PageInfo<>(stus);

        if(stus!=null){
            return new CommonResult<List>(200,"成功",pageInfo.getList());
        }else {
            return new CommonResult<>(400,"失败",null);
        }
    }
    @ApiOperation(value = "添加学生")
    @PostMapping("/students")
    public CommonResult create(@RequestBody Stu stu){
        int i = stuService.create(stu);
        if(i!=0){
            return new CommonResult(200,"添加成功",i);
        }else {
            return new CommonResult(400,"添加失败",null);
        }
    }
}
