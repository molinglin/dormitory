package cn.zust.se.util;


import java.util.List;

public class StartPage {
    public static List startPage(List list, Integer pageNum, Integer pageSize) {
        if (list == null) {
            return null;
        }
        if (list.size() == 0) {
            return null;
        }
        Integer count = list.size();
        int fromIndex = (pageNum - 1) * pageSize;
        int toIndex = pageNum * pageSize;
        if (fromIndex + 1 > count) {
            return null;
        }
        if (pageNum * pageSize > count) {
            toIndex = count;
        }
        List pageList = list.subList(fromIndex, toIndex);
        return pageList;
    }
}