package cn.zust.se.controller;

import cn.zust.se.eneity.CommonResult;
import cn.zust.se.eneity.Stu;
import cn.zust.se.service.ImportService;
import cn.zust.se.service.StuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Api(value = "学生controller",tags ={"学生接口"})
@RestController
public class StuController {
    @Autowired
    StuService stuService;
    @Autowired
    ImportService importService;

    @ApiOperation(value = "根据id获取学生信息")
    @GetMapping("/students/Id/{id}")
    public CommonResult<Stu> getStuById(@ApiParam("id") @PathVariable("id") Integer id){
        Stu stu = stuService.getStuById(id);
        if(stu!=null){
            return new CommonResult<Stu>(200,"查询成功",stu);
        }else {
            return new CommonResult<Stu>(400,"查找失败",null);
        }
    }
    @ApiOperation(value = "根据学号获取学生信息")
    @GetMapping("/students/uid/{uid}")
    public CommonResult<Stu> getStuByUid(@ApiParam("uid") @PathVariable("uid") String uid){
        Stu stu = stuService.getStuByUid(uid);
        if(stu!=null){
            return new CommonResult<Stu>(200,"查询成功",stu);
        }else {
            return new CommonResult<Stu>(400,"查找失败",null);
        }
    }
    @ApiOperation(value = "根据名字获取学生信息")
    @GetMapping("/students/name/{name}")
    public CommonResult<Stu> getStuByName(@ApiParam("name") @PathVariable("name") String name){
        Stu stu = stuService.getStuByName(name);
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

        if(!pageInfo.getList().isEmpty()){
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

    @ApiOperation(value="Excel",notes="返回导入情况接口")
    @PostMapping(value = "/students/excel")
    public CommonResult excelProTbZbzs(@RequestParam("file") MultipartFile file){
        Integer state = (Integer) importService.importStu(file).get("state");
        if(state.equals(200)){
            return new CommonResult<>(200,"添加成功",state);
        }else {
            return new CommonResult<>(500,"添加失败",null);
        }
    }
    @ApiOperation(value="修改寝室信息",notes="修改寝室")
    @PostMapping(value = "/students/update")
    public CommonResult update(@RequestParam("id") Integer id,@RequestParam("dormitory") String dormitory,@RequestParam("buildingid") Integer buildingid,@RequestParam("bednum") Integer bednum){
        int i = stuService.updateDormitory(id, dormitory);
        int j = stuService.updateBuild(id,buildingid);
        int k = stuService.updateBed(id, bednum);
        if(i!=0&&j!=0&&k!=0){
            return new CommonResult(200,"修改成功",1);
        }else {
            return new CommonResult(400,"修改失败",0);
        }
    }
}
