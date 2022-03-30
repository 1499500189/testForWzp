package com.wzp.test.spring注解驱动.spring_annotation.controller;

import com.wzp.test.spring注解驱动.spring_annotation.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date 2022 年 03 月 29 日
 */
@Component
public class Boss {

    private Car car;

     // 也可以放在
    public Boss(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }


    @Autowired //标注再方法上， spring容器创建当前对象，就会调用方法 ，完成赋值
                 //方法使用的参数， 自定义类型的值从ioc容器中获取
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
