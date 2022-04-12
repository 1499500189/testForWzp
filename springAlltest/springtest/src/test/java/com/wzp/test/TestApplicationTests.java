package com.wzp.test;

import com.wzp.test.spring5test.webflux.controller.UserController;
import com.wzp.test.spring5test.webflux.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

@SpringBootTest
class TestApplicationTests {

    @Test
    void contextLoads() {
        UserController controller = new UserController();
        Mono<User> userId = controller.getUserId(3);
        userId.subscribe(System.out::println);


    }

}
