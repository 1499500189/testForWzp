package com.wzp.test.spring注解驱动.spring_annotation.controller;

import com.wzp.test.spring注解驱动.spring_annotation.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author
 * @date 2022 年 03 月 28 日
 */
@Controller
public class BookController {
   @Autowired
   private BookService bookService;
    public  void test(){

        System.out.println("sdadad   sdad");
    }

}
