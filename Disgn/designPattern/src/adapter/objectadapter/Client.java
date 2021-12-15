package adapter.objectadapter;

/**
 * @author
 * @date 2021 年 12 月 03 日
 */
public class Client {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter(new Voltage220v()));
    }
}
