package cn.zust.se.eneity;

import lombok.Data;

import java.util.Date;

@Data
public class Hygiene {
    public Integer id;
    public Date time;
    public Integer buildingid;
    public String dormitory;
    public Integer result;
}
