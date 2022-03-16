package com.wzp.test.redistest;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @author
 * @date 2022 年 03 月 15 日
 */
public class client {
    //秒杀的过程
    public static void main(String[] args) {
        String uid = "用户id";
        String prodid = "产品id";
        //1.传过来两个参数， uid 和prodid非空判断
        if (uid == null || prodid == null) {
            return;  //失败
        }
        //2.连接Jedis
        Jedis jedis = new Jedis("localhost", 6379);
        //3拼接key ， 库存 key  秒杀陈工用户key
        String KcKey = "sk:" + prodid + ":qt";
        String userKey = "sk:" + prodid + ":user";
        //4.获取库存，如果库存null，秒杀还没有开始
        String kc = jedis.get(KcKey);
        if (kc == null) {
            System.out.println("秒杀还没有开始");
            jedis.close();
            return;
        }
        //5。判断用户是否重复秒杀操作
        if (jedis.sismember(userKey, uid)) {
            System.out.println("已经秒杀过了");
            jedis.close();
            return;
        }
        //6判断是如果商品数量，库存数量小于1 ，秒杀结束
        if (Integer.parseInt(kc)<=0){
            System.out.println("商品已经没有了");
            jedis.close();
            return;
        }
        //7.秒杀过程，库存-1，成功的用户添加到清单中
        jedis.decr(KcKey);
        jedis.sadd(userKey,uid);
    }
   //超卖问题
    void chaomai(){
        String uid = "用户id";
        String prodid = "产品id";
        //1.传过来两个参数， uid 和prodid非空判断
        if (uid == null || prodid == null) {
            return;  //失败
        }
        //2.连接Jedis
        Jedis jedis = new Jedis("localhost", 6379);

        //3拼接key ， 库存 key  秒杀陈工用户key
        String KcKey = "sk:" + prodid + ":qt";
        String userKey = "sk:" + prodid + ":user";
        //4.获取库存，如果库存null，秒杀还没有开始
        jedis.watch(KcKey);
        String kc = jedis.get(KcKey);
        if (kc == null) {
            System.out.println("秒杀还没有开始");
            jedis.close();
            return;
        }
        //5。判断用户是否重复秒杀操作
        if (jedis.sismember(userKey, uid)) {
            System.out.println("已经秒杀过了");
            jedis.close();
            return;
        }
        //6判断是如果商品数量，库存数量小于1 ，秒杀结束
        if (Integer.parseInt(kc)<=0){
            System.out.println("商品已经没有了");
            jedis.close();
            return;
        }
        //7.秒杀过程，库存-1，成功的用户添加到清单中
        Transaction multi = jedis.multi();
        multi.decr(KcKey);
        multi.sadd(userKey,uid);
        //执行
        List<Object> exec = multi.exec();
        if (exec==null||exec.size()==0){
            System.out.println("秒杀失败了");
            jedis.close();
        }

    }
}
