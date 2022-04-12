package com.wzp.test.spring注解驱动.jdbctemplate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author
 * @date 2022 年 04 月 08 日
 */
public class test {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void tt(){
        System.out.println("测试使用jdbcTemplate");
        a();
    }

   // @Transactional
    public void  a(){
        System.out.println("a=============");

        b();
        int c=1/0;
    }
    @Transactional
    public void b(){
        System.out.println("b====>");
    }

}
