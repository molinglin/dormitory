package cn.zust.se.eneity;

import lombok.Data;

@Data
public class Application {
    private Integer id;
    private String uid;
    private Integer buildingid;
    private String dormitory;
    private String bednum;
    private Integer access;
    private Integer type;
}
