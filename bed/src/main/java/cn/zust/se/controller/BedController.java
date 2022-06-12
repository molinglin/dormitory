package cn.zust.se.controller;

import cn.zust.se.eneity.Bed;
import cn.zust.se.eneity.Building;
import cn.zust.se.eneity.CommonResult;
import cn.zust.se.eneity.CommonResultBeds;
import cn.zust.se.service.BedService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.Inet4Address;
import java.util.List;

import static cn.zust.se.util.StartPage.startPage;

@Api(value = "bed接口")
@RestController
@RequestMapping( "/beds")
public class BedController {
    @Resource
    BedService bedService;

    @ApiOperation("查找所有空床位")
    @GetMapping("/selE")
    public CommonResult selE(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Bed> beds=bedService.selEmptyBeds();
        PageInfo<Bed> pageInfo=new PageInfo<>(beds);
        if(beds.size()!=0){
            return new CommonResult(200,"success",pageInfo.getList());
        }else {
            return new CommonResult (400,"fail",null);
        }
    }

    @ApiOperation("按楼宇查找空床位")
    @GetMapping("/selEByBuild")
    public CommonResult selEByBuild(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize,Integer buildingid){
        PageHelper.startPage(pageNum,pageSize);
        List<Bed> beds=bedService.selEmptyBedsByBuilding(buildingid);
        PageInfo<Bed> pageInfo=new PageInfo<>(beds);
        if(beds.size()!=0){
            return new CommonResult(200,"success",pageInfo.getList());
        }else {
            return new CommonResult (400,"fail",null);
        }
    }

    @ApiOperation("按楼层查找空床位")
    @GetMapping("/selEByFloor")
    public CommonResult selEByFloor(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize,String floor){
        PageHelper.startPage(pageNum,pageSize);
        List<Bed> beds=bedService.selEmptyBedsByFloor(floor);
        PageInfo<Bed> pageInfo=new PageInfo<>(beds);
        if(beds.size()!=0){
            return new CommonResult(200,"success",pageInfo.getList());
        }else {
            return new CommonResult (400,"fail",null);
        }
    }

    @ApiOperation("按楼宇和楼层查找所有空床位")
    @GetMapping("/selEByBAndF")
    public CommonResult selEByBAndF(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize,Integer buildingid,String floor){
        PageHelper.startPage(pageNum,pageSize);
        List<Bed> beds=bedService.selEmptyBedsByBAndF(buildingid,floor);
        PageInfo<Bed> pageInfo=new PageInfo<>(beds);
        if(beds.size()!=0){
            return new CommonResult(200,"success",pageInfo.getList());
        }else {
            return new CommonResult (400,"fail",null);
        }
    }

    @ApiOperation("查找所有床位")
    @GetMapping("/selAll")
    public CommonResult selAllBeds(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Bed> beds=bedService.selAllBeds();
        PageInfo<Bed> pageInfo=new PageInfo<>(beds);
        if(beds.size()!=0){
            return new CommonResult(200,"success",pageInfo.getList());
        }else {
            return new CommonResult (400,"fail",null);
        }
    }

    @ApiOperation("按楼宇查找所有床位")
    @GetMapping("/selAllByBuild")
    public CommonResult selAllByBuild(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize,Integer buildingid){
        PageHelper.startPage(pageNum,pageSize);
        List<Bed> beds=bedService.selBedsByBuilding(buildingid);
        PageInfo<Bed> pageInfo=new PageInfo<>(beds);
        if(beds.size()!=0){
            return new CommonResult(200,"success",pageInfo.getList());
        }else {
            return new CommonResult (400,"fail",null);
        }
    }

    @ApiOperation("按楼层查找所有床位")
    @GetMapping("/selAllByFloor")
    public CommonResult selAllByFloor(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize,String floor){
        PageHelper.startPage(pageNum,pageSize);
        List<Bed> beds=bedService.selBedsByFloor(floor);
        PageInfo<Bed> pageInfo=new PageInfo<>(beds);
        if(beds.size()!=0){
            return new CommonResult(200,"success",pageInfo.getList());
        }else {
            return new CommonResult (400,"fail",null);
        }
    }

    @ApiOperation("按楼宇和楼层查找所有床位")
    @GetMapping("/selAllByBAndF")
    public CommonResult selAllByBAndF(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize,Integer buildingid,String floor){
        PageHelper.startPage(pageNum,pageSize);
        List<Bed> beds=bedService.selBedsByBAndF(buildingid,floor);
        PageInfo<Bed> pageInfo=new PageInfo<>(beds);
        if(beds.size()!=0){
            return new CommonResult(200,"success",pageInfo.getList());
        }else {
            return new CommonResult (400,"fail",null);
        }
    }

    @ApiOperation("联合查询床位")
    @GetMapping("/selBeds")
    public CommonResultBeds selBeds(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum, @RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize, String buildingid, String dormitory, Integer bednum, String name, String empty){
        if(buildingid=="")buildingid=null;
        if(dormitory=="")dormitory=null;
        if(name=="")name=null;
        if(empty=="")empty=null;
        List<Bed> beds=bedService.selBeds(buildingid, dormitory, bednum, name, empty);
        Integer total=beds.size();
        List<Bed> beds1=startPage(beds,pageNum,pageSize);
//        PageHelper.startPage(pageNum,pageSize);
//        List<Bed> beds1=new ArrayList<>();
//        beds1.addAll(beds);
//        PageInfo<Bed> pageInfo=new PageInfo<>(beds1);
//        pageInfo.getList()
        if(beds.size()!=0){
            return new CommonResultBeds<>(200,"success",total,beds1);
        }else {
            return new CommonResultBeds (400,"fail",0,null);
        }
    }

    @ApiOperation(value = "获取所有楼宇信息")
    @GetMapping ("/showBuilding")
    public CommonResult<List> showBuilding(){
        List<Building> builds =bedService.selBuildings();
        if(builds!=null){
            return new CommonResult<List>(200,"成功",builds);
        }else {
            return new CommonResult<List>(400,"失败",null);
        }
    }
    @PostMapping("/updateUandE")
    public Integer update(@RequestParam("empty")String empty,@RequestParam("uid")String uid,@RequestParam("buildingid") String buildingid, @RequestParam("dormitory") String dormitory,@RequestParam("bednum") Integer bednum){
        int i = bedService.updateUandE(empty,uid,buildingid,dormitory,bednum);
        return i;
    }
    @GetMapping("/findId")
    public Bed findBed(@RequestParam("buildingid") String buildingid, @RequestParam("dormitory") String dormitory,@RequestParam("bednum") Integer bednum){
        Bed bed = bedService.getBed(buildingid,dormitory,bednum);
        return bed;
    }

//    @ApiOperation("联合查询床位")
//    @GetMapping("/selBedss")
//    public CommonResult selBedss(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum, @RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize, String buildingid, String dormitory, String bedNum){
//        PageHelper.startPage(pageNum,pageSize);
//        List<Bed> beds=bedService.selBedss(buildingid, dormitory, bedNum);
//        PageInfo<Bed> pageInfo=new PageInfo<>(beds);
//        if(beds.size()!=0){
//            return new CommonResult(200,"success",pageInfo.getList());
//        }else {
//            return new CommonResult (400,"fail",null);
//        }
//    }
}
