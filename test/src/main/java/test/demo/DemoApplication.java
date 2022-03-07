package test.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.concurrent.locks.StampedLock;

@MapperScan(basePackages ="test.demo")
@SpringBootApplication
public  class DemoApplication {

    public static void main(String[] args) {
     try {
         ArrayList<Object> objects = new ArrayList<>();
         SpringApplication.run(DemoApplication.class, args);
     }catch (Exception e){
         e.printStackTrace();
     }
    }

}
