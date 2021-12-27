package com.wzp.test.absfactory.order;

import com.wzp.test.factorymethid.pizzastore.LDPepperPizza;
import com.wzp.test.factorymethid.pizzastore.Pizza;

/**
 * @author
 * @date 2021 年 12 月 01 日
 */
public class LDOrderPizza extends OrderPizza {
    @Override
    Pizza createPizza(String orderType) {

        Pizza pizza = null;
        if (orderType.equals("cheese")){
         //   pizza=new LDCheesePizza();
        }else if (orderType.equals("pepper")){
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}
