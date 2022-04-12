package com.wzp.test.spring注解驱动.spring_annotation.entity;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author
 * @date 2022 年 03 月 28 日
 */
public class Color {
    @Autowired
    private  Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
