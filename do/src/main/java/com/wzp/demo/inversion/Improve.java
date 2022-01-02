package com.wzp.demo.inversion;

import java.util.HashMap;

/**
 * @author
 * @date 2021 年 10 月 29 日
 */
public class Improve  implements  IReceive{


    @Override
    public String getInfo() {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        Object orDefault = objectObjectHashMap.getOrDefault(1, 0);
        return null;
    }

    @Override
    public String so() {
        return null;
    }
}
interface  IReceive{
    public  String getInfo();

    public String so();
}
