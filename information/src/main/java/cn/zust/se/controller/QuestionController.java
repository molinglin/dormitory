package cn.zust.se.controller;

import cn.zust.se.eneity.CommonResult;
import cn.zust.se.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api("问卷调查")
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Resource
    QuestionService questionService;
    @ApiOperation("插入问卷数据")
    @PostMapping("insertQ")
    public CommonResult insertQ(String uid, Integer sleep, Integer getup, Integer smoke){
        if(questionService.selQ(uid).isEmpty()){
            Integer i=questionService.insertQ(uid, sleep, getup, smoke);
            if(i==1){
                return new CommonResult<>(200,"success",null);
            }else return new CommonResult<>(400,"fail",null);
        }else {
            return new CommonResult<>(400,"已经填写过",null);
        }

    }
}
