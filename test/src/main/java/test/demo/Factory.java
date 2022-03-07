package test.demo;

import org.apache.catalina.core.StandardThreadExecutor;
import test.demo.synchronousQueuep.SynchronousQueueA;
import test.demo.synchronousQueuep.SynchronousQueueB;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.StampedLock;

/**
 * @author
 * @date 2021 年 12 月 01 日
 */
public class Factory {
    private  final StampedLock s1= new StampedLock();
    private  final  String s = "31" ;

    private final   ArrayList<Integer>  a    =   new ArrayList<>() ;
    public static void main(String[] args) {



        
    }
    void moveIfAtOrigin(double newX,double newY){
    String  ss      =   this.s;
        System.out.println(ss);

        ss =  "412";
        System.out.println(ss);

        System.out.println(this.s);

    }

}
