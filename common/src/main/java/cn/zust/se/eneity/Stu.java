package cn.zust.se.eneity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stu implements Serializable {
    private Integer id;
    private String uid;
    private String name;
    private String gender;
    private String phone;
    private String college;
    private String major;
    private String classes;
}
