package com.wzp.test.spring注解驱动.spring_annotation.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

/**
 * @author
 * @date 2022 年 03 月 28 日
 */
public class Red implements ApplicationContextAware , BeanNameAware , EmbeddedValueResolverAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //
    }

    @Override
    public void setBeanName(String s) {

    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        //字符串解析器获取
        String s = resolver.resolveStringValue("你好 ${os.name}");
        System.out.println(s+"《===解析的");
    }
}
