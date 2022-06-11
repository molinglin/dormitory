package cn.zust.se.eneity;

import lombok.Data;

@Data
public class Questionnaire {
    private Integer id;
    private String uid;
    private String name;
    private Integer sleep;
    private Integer getup;
    private Integer temper;
}
