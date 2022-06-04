package cn.zust.se.eneity;

import lombok.Data;

import java.util.Date;

@Data
public class Repair {
    public Integer id;
    public String uid;
    public Date time;
    public Integer buildingid;
    public String dormitory;
    public String intro;
}
