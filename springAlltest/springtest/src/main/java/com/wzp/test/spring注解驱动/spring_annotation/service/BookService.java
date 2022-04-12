package com.wzp.test.spring注解驱动.spring_annotation.service;

import com.wzp.test.spring注解驱动.spring_annotation.dao.BookDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author
 * @date 2022 年 03 月 28 日
 */
@Service
public class BookService {

     @Resource(name = "bookDao2")
     //@Inject
    public BookDao bookDao;

    @Override
    public String toString() {
        return "BookService{" +
                "bookDao=" + bookDao +
                '}';
    }
}
