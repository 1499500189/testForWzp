package com.wzp.test.spring注解驱动.entity;

/**
 * @author
 * @date 2022 年 03 月 28 日
 */
public class Car {
    public Car() {
    }
    public void init(){
        System.out.println("car...init,,,,");
    }
    public void destroy(){
        System.out.println("car....destroy");
    }
}
