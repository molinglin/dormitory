package cn.zust.se.eneity;

import lombok.Data;

import java.util.Date;

@Data
public class Hygiene {
    public Integer id;
    public Integer times;
    public Integer buildingid;
    public String dormitory;
    public Integer result;
}
