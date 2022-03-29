package com.wzp.test.spring注解驱动.controller;

import com.wzp.test.spring注解驱动.dao.BookDao;
import com.wzp.test.spring注解驱动.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @date 2022 年 03 月 28 日
 */
@Service("bookService2")
public class BooService extends BookService {
    @Autowired
    private BookDao bookDao;

}
