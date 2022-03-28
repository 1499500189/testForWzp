package com.wzp.test.spring注解驱动.config;

import com.wzp.test.spring注解驱动.entity.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author
 * @date 2022 年 03 月 28 日
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /*
     *AnnotationMetadata：当前类的注解信息
     * BeanDefinitionRegistry ：BeanDefinition注册类；
     *                  把所有需要添加到容器中的bean：调用
     *                  BeanDefinitionRegistry.registerBeanDefinition手动注册进来
     *
     *  */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
         //如果注册的类中同时有red 和blue 就可以添加
        boolean definition = registry.containsBeanDefinition("mainConfig2");
        boolean definition1 = registry.containsBeanDefinition("person1");
        if (definition&&definition1){
            //指定bean定义信息，
            RootBeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
            registry.registerBeanDefinition("rainBow",beanDefinition);
        }
    }
}
