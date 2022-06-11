package cn.zust.se.controller;

import cn.zust.se.eneity.CommonResult;
import cn.zust.se.eneity.Question;
import cn.zust.se.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api("问卷调查")
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Resource
    QuestionService questionService;
    @ApiOperation("插入问卷数据")
    @PostMapping("insertQ")
    public CommonResult insertQ(@RequestBody Question question){
        if(questionService.selQ(question.getUid()).isEmpty()){
            Integer i=questionService.insertQ(question);
            if(i==1){
                return new CommonResult<>(200,"success",null);
            }else return new CommonResult<>(400,"fail",null);
        }else {
            return new CommonResult<>(400,"已经填写过",null);
        }
    }

    @ApiOperation("根据姓名查问卷数据")
    @GetMapping("selQByName")
    public CommonResult selQByName(String name){
        if(questionService.selQByName(name)!=null){
            return new CommonResult<>(200,"success",questionService.selQByName(name));
        }else {
            return new CommonResult<>(400,"查无此人",null);
        }
    }
}
