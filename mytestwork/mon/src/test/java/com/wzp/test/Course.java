package com.wzp.test;

/**
 * @author
 * @date 2022 年 02 月 07 日
 */
public class Course {
    private  String name;
    private  boolean isAdvanced;
    public Course(String name,boolean isAdvanced){
        this.isAdvanced=isAdvanced;
        this.name=name;
    }
    public boolean isAdvanced(){
        return false;
    }
}
