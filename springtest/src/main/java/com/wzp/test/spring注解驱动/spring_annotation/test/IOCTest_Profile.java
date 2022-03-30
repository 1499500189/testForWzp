package com.wzp.test.spring注解驱动.spring_annotation.test;

import com.wzp.test.spring注解驱动.spring_annotation.config.MainConfigOfProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author
 * @date 2022 年 03 月 29 日
 */
public class IOCTest_Profile {
    @Test
    //使用代码完成环境改变
    public void  test1(){
        //创建
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //设置需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("dev","test");
        //注册主配置类
        applicationContext.register(MainConfigOfProfile.class);
        //启动刷新容器
        applicationContext.refresh();
    }
}
