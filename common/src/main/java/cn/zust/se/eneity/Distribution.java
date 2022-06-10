package cn.zust.se.eneity;

import lombok.Data;

@Data
public class Distribution{
    private String uid;
    private String gender;
    private String name;
    private String college;//1
    private String major;//2
    private String classes;//5
    private Integer sleep;//3
    private Integer getup;//4
    private Integer temper;//6
    private String bid;
//    private String type;
    private String buildingid;
    private String dormitory;
    private String bednum;
}
