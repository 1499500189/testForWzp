package bridge;

/**
 * @author
 * @date 2021 年 12 月 06 日
 */
public class Vivo implements  Brand {
    @Override
    public void open() {
        System.out.println("Vivo手机打电话");
    }

    @Override
    public void close() {
        System.out.println("Vivo手机打电话");
    }

    @Override
    public void call() {
        System.out.println("Vivo手机打电话");
    }
}
