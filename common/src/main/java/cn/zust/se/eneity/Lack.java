package cn.zust.se.eneity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lack {
    public Integer id;
    public String uid;
    public Date time;
    public Integer buildingid;
    public String dormitory;
    public String intro;
}
