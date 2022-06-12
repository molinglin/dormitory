package cn.zust.se.eneity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuApplication {
    private Integer id;
    private String uid1;
    private String uid2;
    private Integer applicationid;
    private Integer access;
}
