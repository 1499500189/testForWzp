package factorymethid.order;


import factorymethid.pizzastore.BJCheesePizza;
import factorymethid.pizzastore.BJPepperPizza;
import factorymethid.pizzastore.Pizza;

/**
 * @author
 * @date 2021 年 12 月 01 日
 */
public class BJOrderPizza extends OrderPizza {
    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("cheese")){
            pizza=new BJCheesePizza();
        }else if (orderType.equals("pepper")){
            pizza = new BJPepperPizza();
        }
        return pizza;
    }
}
