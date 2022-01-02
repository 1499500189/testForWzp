package main.java.test.demo.factory.pizzastore;

/**
 * @author
 * @date 2021 年 11 月 22 日
 */
public class CheesePizza extends  Pizza {
    @Override
    public void bake() {

        System.out.println("给制作奶酪披萨 准备原材料");
    }

}
