package com.wzp.ttt.factory.order;

import com.wzp.ttt.factory.pizzastore.CheesePizza;
import com.wzp.ttt.factory.pizzastore.GreekPizza;
import com.wzp.ttt.factory.pizzastore.Pizza;

/**
 * @author
 * @date 2021 年 11 月 22 日
 */
public class OrderPizza {
    //构造器
    SimpleFactory simpleFactory;
    Pizza  pizza  = null;
    public OrderPizza() {
        String orderType  ;   //订购披萨的类型
        do{
               orderType ="greek";
               if (orderType.equals("greek")){
                   pizza = new GreekPizza();

               }else  if (orderType.equals("cheese")){
                   pizza =new CheesePizza();
               }
        }while(true);
    }


    public OrderPizza(SimpleFactory simpleFactory) {
        setFactory(simpleFactory);

    }

    //构造器

    public  void  setFactory(SimpleFactory simpleFactory){
        String orderType ="";
        this.simpleFactory = simpleFactory;
        do {
            orderType =getType();
         pizza =this.simpleFactory.createPizza(orderType);
            if (pizza!=null){
                //订购成功
                pizza.bake();
                pizza.box();
                pizza.cut();
            }else {
                System.out.println("没有这个种类的披萨");
                break;
            }
        }while (true);

    }

    private String getType() {
        return "greek";
    }

}
