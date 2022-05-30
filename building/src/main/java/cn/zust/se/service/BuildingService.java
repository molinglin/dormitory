package cn.zust.se.service;

import cn.zust.se.eneity.Building;
import cn.zust.se.mapper.BuildingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;
    public List<Building> getallbuilding()
    {
        return buildingMapper.selectList(null);
    }

}
