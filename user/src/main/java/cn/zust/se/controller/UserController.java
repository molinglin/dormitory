package cn.zust.se.controller;

import cn.zust.se.eneity.CommonResult;
import cn.zust.se.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.C;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
//    @ApiOperation(value = "登录")
    @GetMapping("/login")
    @ResponseBody
    public CommonResult<Object> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        String pw = (userService.login(username).getPassword());
        String type = (userService.login(username).getType());
        CommonResult<Object> commonResult = new CommonResult<>();
        if (Objects.equals(pw, password)) {
            commonResult.setCode(200);
            commonResult.setMsg("登录成功！");
            commonResult.setData(userService.login(username));
        } else {
            commonResult.setCode(400);
            commonResult.setMsg("账户名或密码错误");
        }
        return commonResult;
    }

    @GetMapping("/updateUserPw")
    @ResponseBody
    public CommonResult<Object> updateUserPw(@RequestParam("username") String username, @RequestParam("password") String password){
        String pw=(userService.login(username).getPassword());
        if (!Objects.equals(pw, password)){
            userService.updateUserPw(username,password);
            return new CommonResult<>(200,"修改成功");
        }else {
            return new CommonResult<>(400,"密码相同");
        }
    }

    @GetMapping("/updateStu")
    @ResponseBody
    public CommonResult<Object> updateStu(@RequestParam("uid") String uid,@RequestParam("phone") String phone,@RequestParam("college") String college,@RequestParam("major") String major,@RequestParam("classes") String classes){
        if(userService.updateStu(uid, phone, college, major, classes)==1){
            return new CommonResult<>(200,"修改成功");
        }else {
            return new CommonResult<>(400,"修改失败");
        }
    }

    @GetMapping("/updateMaster")
    @ResponseBody
    public CommonResult<Object> updateMaster(@RequestParam("did")String did,@RequestParam("phone")String phone,@RequestParam("building_id")Integer building_id){
        if(userService.updateMaster(did, phone, building_id)==1){
            return new CommonResult<>(200,"修改成功");
        }else {
            return new CommonResult<>(400,"修改失败");
        }
    }
}