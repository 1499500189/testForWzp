package com.wzp.test.spring注解驱动.test;

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
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        System.out.println("容器创建完成");
        BookService bookService = applicationContext.getBean(BookService.class);
        System.out.println(bookService);
        applicationContext.close();
    }
}

