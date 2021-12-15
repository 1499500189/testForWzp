package decorator;

/**
 * @author
 * @date 2021 年 12 月 07 日
 */
public class Coffee  extends  Drink{
    @Override
    public float cost() {

        return super.getPrice();
    }
}
