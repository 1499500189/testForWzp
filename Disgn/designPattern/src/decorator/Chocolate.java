package decorator;

/**
 * @author
 * @date 2021 年 12 月 07 日
 */
//具体的调味品
public class Chocolate extends  Decorator{
    public Chocolate(Drink obj) {
        super(obj);
        setDes("巧克力");
        setPrice(3.0f);
    }
}
