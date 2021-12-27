package com.wzp.ttt.factory.order;

import com.wzp.ttt.factory.pizzastore.Pizza;

/**
 * @author
 * @date 2021 年 11 月 22 日
 */
public class SimpleFactory {
    SimpleFactory simpleFactory;

    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        System.out.println("使用简单的工厂模式");
        return null;
    }
}

