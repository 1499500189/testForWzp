package com.wzp.test.spring注解驱动.service;

import com.wzp.test.spring注解驱动.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
