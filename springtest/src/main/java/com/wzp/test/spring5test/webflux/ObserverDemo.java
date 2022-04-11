package com.wzp.test.spring5test.webflux;

import java.util.Observable;

/**
 * @author
 * @date 2022 年 04 月 11 日
 */
public class ObserverDemo extends Observable {
    public static void main(String[] args) {
        ObserverDemo observer = new ObserverDemo();
        observer.addObserver((o,arg)->{
            System.out.println("发生了变化");
        });
        observer.addObserver((o,arg)->{
            System.out.println("手动被观察者通知， 准备改变");
        });
        System.out.println("----------->");
        observer.setChanged();//数据变化
        System.out.println("--------}");
        observer.notifyObservers();//通知

    }
}
