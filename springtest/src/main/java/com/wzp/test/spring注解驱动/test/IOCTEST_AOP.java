package com.wzp.test.spring注解驱动.test;

import com.wzp.test.spring注解驱动.aop.MathCalculator;
import com.wzp.test.spring注解驱动.config.MainConfigAutoWired;
import com.wzp.test.spring注解驱动.config.MainConfigOfAOP;
import com.wzp.test.spring注解驱动.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author
 * @date 2022 年 03 月 28 日
 */
public class IOCTEST_AOP {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        MathCalculator calculator = (MathCalculator) applicationContext.getBean(MathCalculator.class);
        System.out.println(calculator.getClass());
        calculator.div(1,1);


    }
}

