package com.wzp.test.spring注解驱动.test;

import com.wzp.test.spring注解驱动.config.MainConfigAutoWired;
import com.wzp.test.spring注解驱动.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author
 * @date 2022 年 03 月 28 日
 */
public class IOCTEST_AUTOWIRED {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigAutoWired.class);
        System.out.println("容器创建完成");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String s : beanDefinitionNames) {
            System.out.println(s);
        }
         BookService bookService = (BookService) applicationContext.getBean("bookService");
        System.out.println(bookService);
        String name = bookService.bookDao.getName();
        System.out.println("name ====>" +name);
        Object Boss = applicationContext.getBean("boss");
        System.out.println(Boss);
        applicationContext.close();
    }
}

