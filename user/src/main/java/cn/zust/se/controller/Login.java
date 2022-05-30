package cn.zust.se.controller;

import cn.zust.se.eneity.CommonResult;
import cn.zust.se.eneity.User;
import cn.zust.se.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
public class Login {
    @Resource
    UserService userService;

    @RequestMapping("")
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
            commonResult.setCode(100);
            commonResult.setMsg("账户名或密码错误");
        }
        return commonResult;
    }
}
