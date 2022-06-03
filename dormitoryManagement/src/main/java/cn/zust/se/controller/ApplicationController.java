package cn.zust.se.controller;

import cn.zust.se.eneity.Application;
import cn.zust.se.eneity.CommonResult;
import cn.zust.se.eneity.Stu;
import cn.zust.se.service.ApplicationService;
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

import java.util.List;

@RestController
@Api(value = "申请controller")
@RequestMapping("/applications")
public class ApplicationController {
    @Autowired
    ApplicationService applicationService;
    @Autowired
    StuService stuService;

    @ApiOperation(value = "分页显示所有请求")
    @GetMapping("/")
    public CommonResult pageshow(@ApiParam(value = "pageNum") @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Application> applications = applicationService.selects();
        PageInfo<Application> pageInfo=new PageInfo<>(applications);

        if(!pageInfo.getList().isEmpty()){
            return new CommonResult<List>(200,"成功",pageInfo.getList());
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
    public CommonResult<List<Application>> findNoAccessPage(@ApiParam(value = "pageNum") @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Application> applications = applicationService.selectsNoAccess();
        PageInfo<Application> pageInfo=new PageInfo<>(applications);
        if(!pageInfo.getList().isEmpty()){
            return new CommonResult<>(200, "成功", pageInfo.getList());
        }else {
            return new CommonResult<>(400,"失败",null);
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
    @ApiOperation(value = "同意申请")
    @GetMapping("/agree/{id}")
    private CommonResult agree(@PathVariable("id") Integer id){
        Application application = applicationService.selectById(id);
        Stu stu = stuService.getStuByUid(application.getUid()).getData();
        Integer sid=stu.getId();
        if(application.getType()==0){//换宿申请
            CommonResult result = stuService.update(sid, application.getDormitory(), application.getBuildingid(), application.getBednum());
            if(result.getCode()==400){
                return new CommonResult(400,"修改失败",0);
            }
        }else {
            stuService.update(sid,null,null,null);
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
}
