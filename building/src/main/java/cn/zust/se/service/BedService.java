package cn.zust.se.service;

import cn.zust.se.eneity.Bed;
import cn.zust.se.mapper.BedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedService {
    @Autowired
    private BedMapper bedMapper;
    public List<Bed> getallbed(){
        return bedMapper.selectList(null);
    }
}
