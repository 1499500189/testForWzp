package builder;

/**
 * @author
 * @date 2021 年 12 月 03 日
 */
public class CommonHouse  extends  AbstractHouse{
    @Override
    public void buildBasic() {
        System.out.println("子类重写的");
    }

    @Override
    public void buildWalls() {
        System.out.println("普通房子重写的砌墙");
    }

    @Override
    public void roofed() {
        System.out.println("普通房子封顶");

    }
}
