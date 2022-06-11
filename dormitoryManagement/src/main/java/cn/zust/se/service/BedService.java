package cn.zust.se.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient("dormitory-bed-service")
public interface BedService {
    @PostMapping("/beds/updateUandE")
    public Integer update(@RequestParam("buildingid") String buildingid, @RequestParam("dormitory") String dormitory, @RequestParam("bednum") Integer bednum);
}
