package com.wzp.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class TestApplication {


    /*public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);

*//*

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person bean = applicationContext.getBean(Person.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String s : beanDefinitionNames) {
            System.out.println(s);

        }
        System.out.println("________________________");
        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String s:beanNamesForType) {
            System.out.println(s);
        }
        System.out.println(bean);*//*

    }*/

}
