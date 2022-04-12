package com.wzp.test.spring注解驱动.spring_annotation.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author
 * @date 2022 年 03 月 30 日
 */
//@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Transactional
    public void insertUser(){
        userDao.insert();
        System.out.println("插入完成");
        int i = 1/1;
    }
}
