package com.wzp.test;

/**
 * @author
 * @date 2022 年 01 月 19 日
 */
public class User {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private  String name;
    private  String sex;
    private Integer age;
}
