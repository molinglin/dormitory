package cn.zust.se.service;

import cn.zust.se.eneity.Bed;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient("dormitory-bed-service")
public interface BedService {
    @PostMapping("/beds/updateUandE")
    public Integer update(@RequestParam("empty")String empty,@RequestParam("uid")String uid,@RequestParam("buildingid") String buildingid, @RequestParam("dormitory") String dormitory, @RequestParam("bednum") Integer bednum);
    @GetMapping("/beds/findId")
    public Bed findBed(@RequestParam("buildingid") String buildingid, @RequestParam("dormitory") String dormitory, @RequestParam("bednum") Integer bednum);
}
