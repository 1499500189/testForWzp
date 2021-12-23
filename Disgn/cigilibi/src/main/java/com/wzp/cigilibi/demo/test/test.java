package com.wzp.cigilibi.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author
 * @date 2021 年 12 月 23 日
 */
public class test {
    @Autowired
    private RedisTemplate redisTemplate;

    public  void testtt(){
        System.out.println();
    }

    public static void main(String[] args) {
        test test = new test();
        Boolean delete = test.redisTemplate.delete(2);
        System.out.println(delete);
    }
    public  void  testk(){
        System.out.println("解决debug源码");
        System.out.println("使用SpelExpressionParser");
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
