package com.wzp.cigilibi.demo.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author
 * @date 2022 年 01 月 06 日
 */
public class NotSafeThread implements  Runnable{
      public  static  Number number =new Number();
      public static  int i  =  0;
      public static ThreadLocal<Number> value = new ThreadLocal<Number>(){};
    @Override
    public void run() {
        number.setNum(i++);
        value.set(number);
        //延时2秒
      /*  try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            //ThreadLocal在多线程的情况下的安全性问题 ， 引用都是同一个 ，导致出现安全问题 ，每个线程应该创建自己单独的number
            // TODO Auto-generated catch block
        }*/
        System.out.println("value的值是多少  "+value.get().getNum() );

    }
    public static  void main(String[] args){
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            newCachedThreadPool.execute(new NotSafeThread());

        }
    }
}
