package com.wzp.test.spring注解驱动.config;

import com.wzp.test.spring注解驱动.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配 ；
 *    spring 利用依赖注入（DI） 完成对ioc容器中中各个组件的依赖关系赋值
 *    1)Autowired:自动注入
 *         1)默认优先按照类型去容器中找对应的组件
 *         2)
 *
 * @date 2022 年 03 月 28 日
 */
@ComponentScan({"com.wzp.test.spring注解驱动.controller","com.wzp.test.spring注解驱动.service"})
@Configuration
public class MainConfigAutoWired {
    @Bean
    public BookDao bookDao(){
        BookDao bookDao = new BookDao();
        bookDao.setName("nn");
        return bookDao;
    }
}
