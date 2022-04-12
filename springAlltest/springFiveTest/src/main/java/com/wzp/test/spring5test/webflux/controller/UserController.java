package com.wzp.test.spring5test.webflux.controller;

import com.wzp.test.spring5test.webflux.entity.User;
import com.wzp.test.spring5test.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author
 * @date 2022 年 04 月 11 日
 */
@RestController
public class UserController {
    //注入service
    @Autowired()
    private UserService userService;
    //id查询
    @GetMapping("/user/{id}")
    public Mono<User> getUserId(@PathVariable int id) {
        return userService.getUserById(id);
    }
    //查询所有
    @GetMapping("/user")
    public Flux<User> getUsers(){
        return userService.getAllUsr();
    }
    //添加
    @PostMapping("/saveuser")
    public Mono<Void> saveUser(@RequestBody User user){
        Mono<User> userMono = Mono.just(user);
        return userService.saveUserInfo(userMono);
    }
}
