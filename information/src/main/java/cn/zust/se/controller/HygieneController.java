package cn.zust.se.controller;

import cn.zust.se.eneity.CommonResult;
import cn.zust.se.eneity.Hygiene;
import cn.zust.se.eneity.Inform;
import cn.zust.se.service.HygieneService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "卫生操作接口")
@RestController
@RequestMapping("/hygiene")
public class HygieneController {
    @Resource
    HygieneService hygieneService;

    @ApiOperation("查找所有卫生信息")
    @GetMapping("/selAllHy")
    public CommonResult selAllHy(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Hygiene> hygiene=hygieneService.selAllHy();
        PageInfo<Hygiene> pageInfo=new PageInfo<>(hygiene);
        if(hygiene.isEmpty()){
            return new CommonResult(400,"fail",null);
        }else {
            return new CommonResult<>(200,"success",pageInfo.getList());
        }
    }
}
