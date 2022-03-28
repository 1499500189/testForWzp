package com.wzp.test.spring注解驱动.service;

import com.wzp.test.spring注解驱动.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @date 2022 年 03 月 28 日
 */
@Service
public class BookService {
     @Autowired
    private BookDao bookDao;

    @Override
    public String toString() {
        return "BookService{" +
                "bookDao=" + bookDao +
                '}';
    }
}
