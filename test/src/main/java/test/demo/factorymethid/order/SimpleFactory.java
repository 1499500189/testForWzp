package test.demo.factorymethid.order;

import com.wzp.test.factory.pizzastore.CheesePizza;
import com.wzp.test.factory.pizzastore.GreekPizza;
import com.wzp.test.factory.pizzastore.PepperPizza;
import com.wzp.test.factory.pizzastore.Pizza;

/**
 * @author
 * @date 2021 年 11 月 22 日
 */
public class SimpleFactory {
    SimpleFactory simpleFactory;

    //工厂模式的使用，简单工厂类   将依赖关系变换成聚合关系
    public static Pizza createPizza(String orderType) {
        Pizza pizza = null;
        System.out.println("使用简单的工厂模式");
        //订购披萨的类型

        //orderType = "greek";
        if (orderType.equals("greek")) {
            pizza = new GreekPizza();
            pizza.setName("greek披萨");
        } else if (orderType.equals("cheese")) {
            pizza = new CheesePizza();
            pizza.setName("greek披萨");
        } else if (orderType.equals("pepper")) {
            pizza = new PepperPizza();
            pizza.setName("pepper披萨");
        }

        return pizza;
    }
}

