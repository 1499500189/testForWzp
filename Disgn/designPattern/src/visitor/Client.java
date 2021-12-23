package visitor;

/**
 * @author
 * @date 2021 年 12 月 20 日
 */
public class Client {
    public static void main(String[] args) {
        //创建ObjectStructure
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.attach(new Man());
        objectStructure.attach(new Man());
        objectStructure.attach(new Man());
        //成功
        Success success = new Success();
        objectStructure.display(success);

    }
}
