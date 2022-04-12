package com.wzp.test.spring注解驱动.spring_annotation.test;

import com.wzp.test.spring注解驱动.spring_annotation.aop.MathCalculator;
import com.wzp.test.spring注解驱动.spring_annotation.config.MainConfigOfAOP;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

/**
 * @author
 * @date 2022 年 03 月 28 日
 */
public class IOCTEST_AOP {
    @Test
    public void test01() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        for (String item:list){
            if ("1".equals(item)){
                list.remove(item);
                System.out.println("删除"+item);
            }
        }
    }
}

