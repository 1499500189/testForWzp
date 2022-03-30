package com.wzp.test.spring注解驱动.spring_annotation.test;

import com.wzp.test.spring注解驱动.spring_annotation.tx.TxConfig;
import com.wzp.test.spring注解驱动.spring_annotation.tx.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author
 * @date 2022 年 03 月 28 日
 */
public class IOCTest_tX {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxConfig.class);
        System.out.println("容器创建完成");
        UserService bean = applicationContext.getBean(UserService.class);
        bean.insertUser();
        applicationContext.close();
    }
}

