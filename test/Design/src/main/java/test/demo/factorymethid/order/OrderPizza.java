package main.java.test.demo.factorymethid.order;


import com.wzp.test.factorymethid.pizzastore.Pizza;
import test.demo.factorymethid.order.SimpleFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author
 * @date 2021 年 11 月 22 日
 */
public abstract   class OrderPizza {
    //构造器
    SimpleFactory simpleFactory;
    Pizza pizza = null;

    abstract Pizza createPizza(String orderType);
    public OrderPizza() {
        setFactory();
    }
    //构造器
    public void setFactory() {
        String orderType = "";//用户输入的
        do {
            orderType = getType();
          pizza = createPizza(orderType);
            if (pizza != null) {
                //订购成功
                pizza.bake();
                pizza.box();
                pizza.cut();
            } else {
                System.out.println("没有这个种类的披萨");
                break;
            }
        } while (true);
    }
    private String getType() {
      do {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          String read1 = null;
          System.out.println("输入数据：");
          try {
              read1 = br.readLine();
          } catch (IOException e) {
              e.printStackTrace();
          }
          return read1;
      }while (true);
    }



}
