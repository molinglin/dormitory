package cn.zust.se.mapper;

import cn.zust.se.eneity.Building;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BuildingMapper extends BaseMapper<Building> {


}
