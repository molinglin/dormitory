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
        int i=0,j=0;
        Application application = applicationService.selectById(id);
        Stu stu = stuService.getStuByUid(application.getUid()).getData();
        System.out.println(stu);
        Integer sid=stu.getId();
        String dormitory = stu.getDormitory();
        Integer buildingid = stu.getBuildingid();
        Integer bednum = stu.getBednum();
        if(application.getType()==0){//换宿申请
            Bed bed = bedService.findBed(String.valueOf(application.getBuildingid()),application.getDormitory(),application.getBednum());
            System.out.println(bed);
            if(bed==null){
                return new CommonResult(400,"没有此床位");
            }
            if(bed.getEmpty().equals("N")){
                Stu data = stuService.getStuByUid(bed.getUid()).getData();//调换对象
                System.out.println(data);
                List<Application> applications = applicationService.selectByUidAndNoAccess(data.getUid());
                if(applications.isEmpty()){
                    return new CommonResult(400,"对方未发起调换",null);
                }
                Application applicationData=applications.get(0);
                if((!applicationData.getDormitory().equals(stu.getDormitory()))||(!applicationData.getBuildingid().equals(stu.getBuildingid()))||(!applicationData.getBednum().equals(stu.getBednum()))){
                    return new CommonResult(400,"同意失败",null);
                }
                int i1=bedService.update("N",stu.getUid(), String.valueOf(application.getBuildingid()),application.getDormitory(),application.getBednum());
                int c1 = stuService.update(sid, application.getDormitory(), application.getBuildingid(), application.getBednum()).getCode();
                int i2=bedService.update("N",data.getUid(), String.valueOf(stu.getBuildingid()),stu.getDormitory(),stu.getBednum());
                int c2=stuService.update(data.getId(), stu.getDormitory(), stu.getBuildingid(), stu.getBednum()).getCode();
                if(i1==0||i2==0||c1==400||c2==40){
                    return new CommonResult(400,"同意失败");
                }
                i=applicationService.agree(id);
                j=applicationService.agree(applicationData.getId());
//                return new CommonResult(400,"申请的寝室不为空");
            }else {
                bedService.update("Y","null",String.valueOf(buildingid),dormitory,bednum);//学生对应床位置为空
                CommonResult result = stuService.update(sid, application.getDormitory(), application.getBuildingid(), application.getBednum());
                Integer n = bedService.update("N", stu.getUid(), String.valueOf(application.getBuildingid()), application.getDormitory(), application.getBednum());
                if(n==0){
                    return new CommonResult(400,"修改失败");
                }
                if(result.getCode()==400){
                    return new CommonResult(400,"修改失败",0);
                }
                i=applicationService.agree(id);
            }
        }else {
            i = applicationService.agree(id);
            bedService.update("Y","null",String.valueOf(buildingid),dormitory,bednum);//学生对应床位置为空
            stuService.update(sid, "null",0,0);
        }

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
//    @ApiOperation("查看学生请求")
//    @GetMapping("/findStuA")
//    public CommonResult<List<StuApplication>> findStuApplication(){
//        List<StuApplication> noAccess = service.findNoAccess();
//        if(!noAccess.isEmpty()){
//            return new CommonResult<>(200,"查找成功",noAccess);
//        }else {
//            return new CommonResult<>(400,"查找失败",null);
//        }
//    }
//    @ApiOperation("学生端同意请求")
//    @GetMapping("/stuagree/{id}")
//    public CommonResult StuAgree(@PathVariable("id") Integer id){
//        StuApplication s = service.findById(id);
//        Stu stu1 = stuService.getStuByUid(s.getUid1()).getData();
//        Stu stu2 = stuService.getStuByUid(s.getUid2()).getData();
//        Application application = applicationService.selectById(s.getApplicationid());
//        Bed bed1 = bedService.findBed(String.valueOf(stu1.getBuildingid()), stu1.getDormitory(), stu1.getBednum());
//        Bed bed2=bedService.findBed(String.valueOf(stu2.getBuildingid()),stu2.getDormitory(),stu2.getBednum());
//        CommonResult update1 = stuService.update(stu1.getId(), stu2.getDormitory(), stu2.getBuildingid(), stu2.getBednum());
//        CommonResult update2=stuService.update(stu2.getId(), stu1.getDormitory(), stu1.getBuildingid(), stu1.getBednum());
//        Integer n1 = bedService.update("N", stu1.getUid(), String.valueOf(stu2.getBuildingid()), stu2.getDormitory(), stu2.getBednum());
//        Integer n2=bedService.update("N",stu2.getUid(), String.valueOf(stu1.getBuildingid()),stu1.getDormitory(),stu1.getBednum());
//        if((update1.getCode()==400)||(update2.getCode()==400)||n1==0||n2==0){
//            return new CommonResult(400,"修改失败",null);
//        }
//        int agree1 = service.agree(id);
//        int agree = applicationService.agree(s.getApplicationid());
//        if(agree1!=0&&agree!=0){
//            return new CommonResult(200,"修改成功");
//        }else {
//            return new CommonResult(400,"修改失败");
//        }
//    }
//    @PostMapping("/allocation")
//    public CommonResult allocation(){
//        List<Stu> men = stuService.getMen();
//        stuService.getWomen();
//    }
}
