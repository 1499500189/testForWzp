package com.wzp.test.spring注解驱动.entity;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author
 * @date 2022 年 03 月 28 日
 */
@Component
public class Dog implements ApplicationContextAware {
    private  ApplicationContext applicationContext;
    public  Dog(){
        System.out.println("dog  constructor....");
    }
    //构造器创建并赋值之后调用
    @PostConstruct
    public void init(){
        System.out.println("init");
    }
    @PreDestroy
    public  void  destroy(){
        System.out.println("destroy");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //在其他的方法使用
        this.applicationContext =applicationContext;
    }
}
