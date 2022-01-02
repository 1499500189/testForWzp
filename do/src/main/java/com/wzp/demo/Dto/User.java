package com.wzp.demo.Dto;

import lombok.Data;

/**
 * @author
 * @date 2021 年 11 月 09 日
 */
@Data
public class User {
    private  String id;

    public User(String name) {
        this.name = name;
    }

    private  String name;

    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
