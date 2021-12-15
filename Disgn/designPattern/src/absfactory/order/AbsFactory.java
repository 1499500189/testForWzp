package absfactory.order;


import absfactory.pizzastore.Pizza;

/**
 * @author
 * @date 2021 年 12 月 01 日
 */
public interface AbsFactory {
    public Pizza createPizza(String orderType);
}
