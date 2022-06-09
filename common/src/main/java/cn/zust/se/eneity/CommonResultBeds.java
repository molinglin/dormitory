package cn.zust.se.eneity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResultBeds<T> {

    private Integer code;
    private String msg;
    private Integer total;
    private T data;


    public CommonResultBeds(Integer code, String msg,Integer total){
        this(code,msg,total,null);
    }


}
