package main.java.test.demo.absfactory.order;

import com.wzp.test.absfactory.pizzastore.BJCheesePizza;
import com.wzp.test.absfactory.pizzastore.BJPepperPizza;
import com.wzp.test.absfactory.pizzastore.Pizza;
import test.demo.absfactory.order.AbsFactory;

/**
 * @author
 * @date 2021 年 12 月 01 日
 */
public class BJFactory  implements AbsFactory {
    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza =null;
        if (orderType.equals("cheese")){
            pizza = new BJCheesePizza();
        }else if (orderType.equals("pepper")){
            pizza = new BJPepperPizza();
        }
        return pizza;
    }
}
