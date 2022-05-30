package cn.zust.se.controller;

import cn.zust.se.eneity.CommonResult;
import cn.zust.se.eneity.Stu;
import cn.zust.se.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stu")
public class StuController {
    @Autowired
    StuService stuService;

    @GetMapping("/get/{id}")
    public CommonResult<Stu> getStuById(@PathVariable("id") Integer id){
        Stu stu = stuService.getStuById(id);
        if(stu!=null){
            return new CommonResult<Stu>(200,"查询成功",stu);
        }else {
            return new CommonResult<Stu>(400,"查找失败",null);
        }
    }

}
