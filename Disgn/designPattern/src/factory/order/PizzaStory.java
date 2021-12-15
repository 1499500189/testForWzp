package factory.order;



/**
 * @author
 * @date 2021 年 11 月 23 日
 */
public class PizzaStory {
    public static void main(String[] args) {

        new OrderPizza(new SimpleFactory());
        System.out.println("推出程序");
    }
}
