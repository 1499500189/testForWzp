package com.wzp.test.spring注解驱动.spring_annotation.config;

import com.wzp.test.spring注解驱动.spring_annotation.service.Person;

import com.wzp.test.spring注解驱动.spring_annotation.TypeFilter.MyTypeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author
 * @date 2022 年 03 月 28 日
 */
//配置类==配置文
    //excludeFilters 排除组件
    //includeFilters 只扫描
    //FilterType.ANNOTATION按照注解
    //FilterType.ASSIGNABLE_TYPE :按照给定的类型
    //FilterType。ASPECTJ 使用aspectj表达式
    //FilterType。REGEX 使用正则指定
    //FilterType。CYSTOM 使用自定义规则
    //多个filter应该能处理
@ComponentScan(basePackages = "com.wzp.test",
        includeFilters =
                {     @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class}),
                        @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {MyTypeFilter.class})

                        /* @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {BookService.class})*/
                },
        useDefaultFilters = false)

/*@ComponentScan(basePackages = "com.wzp.test",
        includeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Service.class})},
        useDefaultFilters = false)*/
//只包含使用useDefaultFilters使include生效
@Configuration
public class MainConfig {
    //给容器中注册一个bean；类型为返回值的类型， id默认是用
    //如果不通过bean指定，那么他的名字是person1，bean指定是person2
    @Bean("person2")
    public Person person1(){
        return new Person("lisi",22);
    }
}
