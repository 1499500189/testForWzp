package com.wzp.test.spring5test.webflux.service;

import com.wzp.test.spring5test.webflux.entity.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author
 * @date 2022 年 04 月 11 日
 */
//用户操作接口
@Service
public interface UserService {
    //根据id查询用户
    Mono<User> getUserById(int id);
    //查询所有用户
    Flux<User> getAllUsr();
    //添加用户
    Mono<Void> saveUserInfo(Mono<User> user);

}
