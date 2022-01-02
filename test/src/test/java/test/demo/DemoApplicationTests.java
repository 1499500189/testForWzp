package test.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void test1() {
        Set<Integer> ss = new HashSet<>();
        ss.add(1);
        ss.add(2);
        ss.add(3);
        ss.add(1);

        System.out.println(ss);
    }


}
