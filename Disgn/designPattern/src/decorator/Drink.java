package decorator;

/**
 * @author
 * @date 2021 年 12 月 07 日
 */
public abstract class Drink {
    public  String des;
    private  float price = 0.0f;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    //计算费用的抽象方法
    public abstract  float cost();
}
