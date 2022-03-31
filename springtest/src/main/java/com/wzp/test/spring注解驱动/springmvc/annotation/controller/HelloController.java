package com.wzp.test.spring注解驱动.springmvc.annotation.controller;


import com.wzp.test.spring注解驱动.springmvc.annotation.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Autowired
    HelloService helloService;


    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        String hello = helloService.sayHello("tomcat..");
        return hello;
    }

    //  /WEB-INF/views/success.jsp
    @RequestMapping("/suc")
    public String success(){
        return "success";
    }


}
