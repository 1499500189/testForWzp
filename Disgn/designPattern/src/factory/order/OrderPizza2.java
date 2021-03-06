package factory.order;



import factory.pizzastore.CheesePizza;
import factory.pizzastore.GreekPizza;
import factory.pizzastore.PepperPizza;
import factory.pizzastore.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author
 * @date 2021 年 11 月 22 日
 */
public class OrderPizza2 {
    //构造器
    SimpleFactory simpleFactory;
    Pizza pizza = null;

    public OrderPizza2() {
        String orderType;   //订购披萨的类型
        do {
            orderType = "greek";
            if (orderType.equals("greek")) {
                pizza = new GreekPizza();

            } else if (orderType.equals("cheese")) {
                pizza = new CheesePizza();
            }else if (orderType.equals("pepper")){
                pizza =new PepperPizza();
            }
        } while (true);
    }


    public OrderPizza2(SimpleFactory simpleFactory) {
        setFactory(simpleFactory);

    }

    //构造器

    public void setFactory(SimpleFactory simpleFactory) {
        String orderType = "";//用户输入的
        this.simpleFactory = simpleFactory;
        do {
            orderType = getType();
            pizza = SimpleFactory.createPizza(orderType);
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
