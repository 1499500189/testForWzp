package com.wzp.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wzp.test.mapper")
public class ShardingjdbctestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingjdbctestApplication.class, args);
    }

}
