package com.wzp.test.spring注解驱动.dao;

import org.springframework.stereotype.Repository;

/**
 * @author
 * @date 2022 年 03 月 28 日
 */
@Repository
public class BookDao {
    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
