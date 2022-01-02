package com.wzp.user.benmon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@SpringBootApplication
public class BenmonApplication {

    public static void main(String[] args) {
       // ApplicationContext a = new ClassPathXmlApplicationContext("beans.xml");
     //   Object nanme = a.getBean("nanme");
        SpringApplication.run(BenmonApplication.class, args);
    }

}
