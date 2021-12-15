package decorator;

/**
 * @author
 * @date 2021 年 12 月 07 日
 */
public class Decorator extends  Drink {
    private  Drink obj;

    public Decorator(Drink obj) {
        this.obj=obj;
    }

    @Override
    public float cost() {
        //先拿到自己的价格，之后去组合一个饮料
        return super.getPrice()+obj.getPrice();
    }

    @Override
    public String getDes() {
        //obj.getDes(）输出了被装饰者的信息
        return super.des+""+super.getPrice()+"&&"+obj.getDes();

    }
}
