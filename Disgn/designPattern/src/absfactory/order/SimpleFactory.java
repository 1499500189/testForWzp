package absfactory.order;


import absfactory.pizzastore.BJCheesePizza;
import absfactory.pizzastore.BJPepperPizza;
import absfactory.pizzastore.Pizza;
import factory.pizzastore.GreekPizza;
import factory.pizzastore.PepperPizza;


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
            pizza = new BJCheesePizza();
            pizza.setName("greek披萨");
        } else if (orderType.equals("cheese")) {
            pizza = new BJPepperPizza();
            pizza.setName("greek披萨");
        }
        return pizza;
    }
}

