package bridge;

/**
 * @author
 * @date 2021 年 12 月 06 日
 */
public class Client {
    public static void main(String[] args) {
        Phone phone = new FoldedPhone(new XiaoMi());
        phone.open();
        phone.call();
        phone.close();
        Phone upRightPhone = new UpRightPhone(new XiaoMi());
        upRightPhone.close();
        upRightPhone.call();
    }
}
