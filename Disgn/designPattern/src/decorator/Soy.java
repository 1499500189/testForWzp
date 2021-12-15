package decorator;

/**
 * @author
 * @date 2021 年 12 月 07 日
 */
public class Soy extends  Decorator {
    public Soy(Drink obj) {
        super(obj);

        setDes("豆浆");
        setPrice(1.5f);
    }
}
