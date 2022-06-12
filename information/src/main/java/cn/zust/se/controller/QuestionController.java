package cn.zust.se.controller;

import cn.zust.se.eneity.CommonResult;
import cn.zust.se.eneity.CommonResultBeds;
import cn.zust.se.eneity.Question;
import cn.zust.se.eneity.Questionnaire;
import cn.zust.se.service.QuestionService;
import cn.zust.se.util.StartPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api("问卷调查")
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Resource
    QuestionService questionService;
    @ApiOperation("插入问卷数据")
    @PostMapping("insertQ")
    public CommonResult insertQ(@RequestBody Question question){
        List<Question> questions = questionService.selQ(question.getUid());
        if(questions.isEmpty()){
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

    @ApiOperation("联合查询问卷")
    @GetMapping("/selQs")
    public CommonResultBeds selQs(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum, @RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize, String uid, Integer sleep, Integer getup, String temper, String name){
        if(uid=="")uid=null;
        if(name=="")name=null;
        List<Questionnaire> questionnaires=questionService.selQs(uid, sleep, getup, temper, name);
        Integer total=questionnaires.size();
        List<Questionnaire> questionnaires1= StartPage.startPage(questionnaires,pageNum,pageSize);
        if(questionnaires!=null){
            return new CommonResultBeds(200,"success",total,questionnaires1);
        }else {
            return new CommonResultBeds(400,"查无此人",null);
        }
    }
}
