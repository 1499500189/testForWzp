package com.wzp.test.spring注解驱动.entity;

import org.springframework.beans.factory.annotation.Value;

/**
 * @使用@Value赋值：
 *  1.基本数值
 *  2.可以写SpEL；#{}
 *  3.可以写${}；取出配置文件中的值（在循行环境变量里面的值）
 * @date 2022 年 03 月 28 日
 */
public class Person2 {
    @Value("${person.name}")
    private  String name;
    @Value("11")
    private Integer age;

    public Person2() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person2(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
