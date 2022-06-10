package cn.zust.se.eneity;

import lombok.Data;

import java.util.Date;

@Data
public class Inform {
    public Integer id;
    public Date time;
    public String publisher;
    public String content;
    public String title;
}
