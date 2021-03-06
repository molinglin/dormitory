package cn.zust.se.controller;

import cn.zust.se.eneity.Admin;
import cn.zust.se.eneity.CommonResult;
import cn.zust.se.eneity.Master;
import cn.zust.se.eneity.Stu;
import cn.zust.se.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.C;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
@Api(value = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    @ApiOperation(value = "登录")
    @GetMapping("/login")
    @ResponseBody
    public CommonResult<Object> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        String pw = (userService.login(username).getPassword());
        String type = (userService.login(username).getType());
        CommonResult<Object> commonResult = new CommonResult<>();
        if(Objects.equals(type, "1")){
            Stu stu=userService.selStu(username);
            stu.setType(type);
            commonResult.setData(stu);
        } else if (Objects.equals(type,"2")) {
            Master master=userService.selMaster(username);
            master.setType(type);
            commonResult.setData(master);
        }else if(Objects.equals(type,"0")){
            Admin admin=new Admin();
            admin.setType(type);
            admin.setUsername("admin");
            commonResult.setData(admin);
        }
        if (Objects.equals(pw, password)) {
            commonResult.setCode(200);
            commonResult.setMsg("登录成功！");
//            commonResult.setData(userService.login(username));
        } else {
            commonResult.setCode(400);
            commonResult.setMsg("账户名或密码错误");
            commonResult.setData(null);
        }
        return commonResult;
    }

    @ApiOperation("修改密码")
    @PutMapping("/updateUserPw")
    @ResponseBody
    public CommonResult<Object> updateUserPw(@RequestParam("username") String username, @RequestParam("password") String password){
        String pw=(userService.login(username).getPassword());
        if (!Objects.equals(pw, password)){
            if(userService.updateUserPw(username,password)==1){
                return new CommonResult<>(200,"修改成功");
            }else
                return new CommonResult<>(400,"修改失败");
        }else {
            return new CommonResult<>(400,"密码相同");
        }
    }

    @ApiOperation("修改学生信息")
    @PutMapping("/updateStu")
    @ResponseBody
    public CommonResult<Object> updateStu(String uid,String phone,String college,String major,String classes){
        if(userService.updateStu(uid, phone, college, major, classes)==1){
            return new CommonResult<>(200,"修改成功");
        }else {
            return new CommonResult<>(400,"修改失败");
        }
    }

    @ApiOperation("修改宿管信息")
    @PutMapping("/updateMaster")
    @ResponseBody
    public CommonResult<Object> updateMaster(String did,String name,String phone,Integer buildingid){
        if(userService.updateMaster(did, name,phone, buildingid)==1){
            return new CommonResult<>(200,"修改成功");
        }else {
            return new CommonResult<>(400,"修改失败");
        }
    }

    @ApiOperation("修改宿管权限")
    @PutMapping("/updatePer")
    public CommonResult updatePer(String buildingid,String did){
        if(userService.updatePermissions(buildingid, did)==1){
            return new CommonResult<>(200,"success");
        }else {
            return new CommonResult<>(400,"fail");
        }
    }

    @ApiOperation("查找所有宿管")
    @PutMapping("/selAllMaster")
    public CommonResult selAllMaster(){
        if(!userService.selAllMaster().isEmpty()){
            return new CommonResult<>(200,"success",userService.selAllMaster());
        }else {
            return new CommonResult<>(400,"fail");
        }
    }
}
