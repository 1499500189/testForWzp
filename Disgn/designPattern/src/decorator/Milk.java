package decorator;

/**
 * @author
 * @date 2021 年 12 月 07 日
 */
public class Milk extends  Decorator{
    public Milk(Drink obj) {
        super(obj);
        setDes("牛奶");
        setPrice(2.0f);
    }
}
