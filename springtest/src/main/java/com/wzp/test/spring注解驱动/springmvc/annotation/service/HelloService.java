package com.wzp.test.spring注解驱动.springmvc.annotation.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String sayHello(String name){

        return "Hello "+name;
    }

}
