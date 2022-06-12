package cn.zust.se.controller;

import cn.zust.se.eneity.*;
import cn.zust.se.service.ApplicationService;
import cn.zust.se.service.BedService;
import cn.zust.se.service.StuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.apache.commons.lang.ObjectUtils;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "申请controller")
@RequestMapping("/applications")
public class ApplicationController {
    @Autowired
    ApplicationService applicationService;
    @Autowired
    StuService stuService;
    @Autowired
    BedService bedService;

    @ApiOperation(value = "分页显示所有请求")
    @GetMapping("/")
    public CommonResult pageshow(@ApiParam(value = "pageNum") @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@ApiParam("pageSize") @RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Application> applications = applicationService.selects();
        PageInfo<Application> pageInfo=new PageInfo<>(applications);

        if(!pageInfo.getList().isEmpty()){
            return new CommonResult<>(200,"成功",pageInfo);
        }else {
            return new CommonResult<>(400,"失败",null);
        }
    }
    @ApiOperation(value = "待办请求")
    @GetMapping("/NoAccess")
    public CommonResult<List<Application>> findNoAccess(){
        List<Application> applications = applicationService.selectsNoAccess();
        if(!applications.isEmpty()){
            return new CommonResult<>(200, "成功", applications);
        }else {
            return new CommonResult<>(400,"失败",null);
        }
    }
    @ApiOperation(value = "分页显示待办请求")
    @GetMapping("/NoAccessPage")
    public CommonResult<PageInfo> findNoAccessPage(@ApiParam(value = "pageNum") @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@ApiParam("pageSize") @RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Application> applications = applicationService.selectsNoAccess();
        PageInfo<Application> pageInfo=new PageInfo<>(applications);
        if(!pageInfo.getList().isEmpty()){
            return new CommonResult<>(200, "成功", pageInfo);
        }else {
            return new CommonResult<>(400,"失败",null);
        }
    }
    @ApiOperation(value = "获取所有维修信息")
    @GetMapping ("/showRepair")
    public CommonResult<List> showRepair(){
        List<Repair> repairs =applicationService.selRepair();
        if(repairs!=null){
            return new CommonResult<List>(200,"成功",repairs);
        }else {
            return new CommonResult<List>(400,"失败",null);
        }
    }
    @ApiOperation(value = "新增维修请求")
    @PostMapping("/insertRepair")
    public CommonResult insertRepair(@RequestBody  Repair repair){
        int i = applicationService.insertRepair(repair);
        if(i!=0){
            return new CommonResult(200,"添加成功",i);
        }else {
            return new CommonResult(400,"添加失败",i);
        }
    }
    @ApiOperation(value = "处理报修")
    @PutMapping("/upRepair/{id}")
    public CommonResult upRepair(@ApiParam("id")  @PathVariable("id") Integer id){
        int i = applicationService.upRepair(id);
        if(i!=0){
            return new CommonResult(200,"处理成功",i);
        }else {
            return new CommonResult(400,"处理失败",i);
        }
    }
    @ApiOperation(value = "验收报修")
    @PutMapping("/upRepair2/{id}")
    public CommonResult upRepair2(@ApiParam("id")  @PathVariable("id") Integer id){
        int i = applicationService.upRepair2(id);
        if(i!=0){
            return new CommonResult(200,"处理成功",i);
        }else {
            return new CommonResult(400,"处理失败",i);
        }
    }
    @ApiOperation(value = "根据id查找请求")
    @GetMapping("/{id}")
    public CommonResult<Application> findById(@ApiParam("id")  @PathVariable("id") Integer id){
        Application application = applicationService.selectById(id);
        if(application!=null){
            return new CommonResult<Application>(200,"查找成功",application);
        }else {
            return new CommonResult<>(400,"查找失败",null);
        }
    }
    @ApiOperation(value = "根据学号查找请求")
    @GetMapping("/uid/{uid}")
    public CommonResult<List<Application>> findByUid(@ApiParam("id")  @PathVariable("uid") Integer uid){
        List<Application> applications = applicationService.selectByUid(uid);
        if(!applications.isEmpty()){
            return new CommonResult<>(200,"查找成功",applications);
        }else {
            return new CommonResult<>(400,"查找失败",null);
        }
    }
    @ApiOperation(value = "新增换宿请求")
    @PostMapping("/insertchange")
    public CommonResult insertchange(@RequestBody  Application application){
        int i = applicationService.changeDormitory(application);
        if(i!=0){
            return new CommonResult(200,"添加成功",i);
        }else {
            return new CommonResult(400,"添加失败",i);
        }
    }
    @ApiOperation(value = "新增退宿请求")
    @PostMapping("/insertquit")
    public CommonResult insertquit(@RequestBody Application application){
        int i = applicationService.quitDormitory(application);
        if(i!=0){
            return new CommonResult(200,"添加成功",i);
        }else {
            return new CommonResult(400,"添加失败",i);
        }
    }
    @ApiOperation(value = "取消申请")
    @GetMapping("/delete/{id}")
    private CommonResult cancel(@PathVariable("id") Integer id){
        int i = applicationService.cancel(id);
        if(i!=0){
            return new CommonResult(200,"删除成功",i);
        }else {
            return new CommonResult(400,"删除失败",i);
        }
    }
    @ApiOperation(value = "管理员同意申请")
    @GetMapping("/agree/{id}")
    private CommonResult agree(@PathVariable("id") Integer id){
        Application application = applicationService.selectById(id);
        Stu stu = stuService.getStuByUid(application.getUid()).getData();
        System.out.println(stu);
        Integer sid=stu.getId();
        String dormitory = stu.getDormitory();
        Integer buildingid = stu.getBuildingid();
        Integer bednum = stu.getBednum();
        if(application.getType()==0){//换宿申请
            Bed bed = bedService.findBed(String.valueOf(application.getBuildingid()),application.getDormitory(),application.getBednum());
            if(bed==null){
                return new CommonResult(400,"没有此床位");
            }
            if(bed.getEmpty().equals("N")){
                Stu data = stuService.getStuByUid(bed.getUid()).getData();//调换对象
//                return new CommonResult(400,"申请的寝室不为空");
            }
            bedService.update("Y","null",String.valueOf(buildingid),dormitory,bednum);//学生对应床位置为空
            CommonResult result = stuService.update(sid, application.getDormitory(), application.getBuildingid(), application.getBednum());
            Integer n = bedService.update("N", stu.getUid(), String.valueOf(application.getBuildingid()), application.getDormitory(), application.getBednum());
            if(n==0){
                return new CommonResult(400,"修改失败");
            }
            if(result.getCode()==400){
                return new CommonResult(400,"修改失败",0);
            }
        }else {
            bedService.update("Y","null",String.valueOf(buildingid),dormitory,bednum);//学生对应床位置为空
            stuService.update(sid, "null",0,0);
        }
        int i = applicationService.agree(id);
        if(i!=0){
            return new CommonResult(200,"同意成功",i);
        }else {
            return new CommonResult(400,"同意失败",i);
        }
    }
    @ApiOperation(value = "拒绝申请")
    @GetMapping("/reject/{id}")
    private CommonResult reject(@PathVariable("id") Integer id){
        int i = applicationService.reject(id);
        if(i!=0){
            return new CommonResult(200,"拒绝成功",i);
        }else {
            return new CommonResult(400,"拒绝失败",i);
        }
    }
//    @PostMapping("/allocation")
//    public CommonResult allocation(){
//        List<Stu> men = stuService.getMen();
//        stuService.getWomen();
//    }
}
