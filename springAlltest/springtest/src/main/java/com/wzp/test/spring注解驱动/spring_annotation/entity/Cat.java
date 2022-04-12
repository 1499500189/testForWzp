package com.wzp.test.spring注解驱动.spring_annotation.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date 2022 年 03 月 28 日
 */
@Component
public class Cat implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("cat....destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("cat ........ afterPropertiesSet");
    }
}
