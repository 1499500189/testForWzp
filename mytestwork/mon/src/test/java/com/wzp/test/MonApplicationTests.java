package com.wzp.test;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
@RunWith(SpringRunner.class)
/*@RunWith(SpringJUnit4ClassRunner.class)*/
/*@ActiveProfiles()*/
@SpringBootTest
@SpringBootConfiguration
class MonApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void test1() {
        Map<String, Object> s = new HashMap<>();
        s.put("issueTask",1);
        s.put("iss","jssfd00");
        Object o = s.get("issueTask");
        Object iss1 = s.get("iss");
        Integer iss = null;
        if (o.getClass()==Integer.class){
            iss = (Integer) s.get("issueTask");
            System.out.println("++++++++++++++++++");
        }else {
           iss = 0;
        }
        System.out.println("iss的值是多少"+iss);




        if (iss1.getClass()==Integer.class){
            Integer iss3 = (Integer) s.get("issueTask");
            System.out.println("++++++++++++++++++");
            System.out.println("iss3"+iss3);
        }else {
            Integer iss3 = 0;
            System.out.println("iss3"+iss3);
        }
            System.out.println(o.getClass().getName());
    }

    @Test
    void test3(){

    }

}
