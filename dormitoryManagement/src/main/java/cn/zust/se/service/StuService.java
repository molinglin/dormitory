package cn.zust.se.service;

import cn.zust.se.eneity.CommonResult;
import cn.zust.se.eneity.Stu;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient("dormitory-student-service")
public interface StuService {
    @GetMapping("/students/uid/{uid}")
    public CommonResult<Stu> getStuByUid(@ApiParam("uid") @PathVariable("uid") String uid);
    @PostMapping(value = "/students/update")
    public CommonResult update(@RequestParam("id") Integer id,@RequestParam(value = "dormitory",required = false) String dormitory,@RequestParam(value = "buildingid",required = false) Integer buildingid,@RequestParam(value = "bednum",required = false) Integer bednum);
    @GetMapping("/students/man")
    public List<Stu> getMen();
    @GetMapping("/students/women")
    public List<Stu> getWomen();
}
