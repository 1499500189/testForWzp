package adapter.classadapter;

/**
 * @author
 * @date 2021 年 12 月 03 日
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("类的适配器模式");
        Phone phone =new Phone();
        phone.charging(new VoltageAdapter());
    }
}
