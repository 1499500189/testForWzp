package adapter.interfaceadapter;

/**
 * @author
 * @date 2021 年 12 月 03 日
 */
public class Client {
    public static void main(String[] args) {
        AbsAdapter absAdapter = new AbsAdapter() {
            @Override
            public void v4() {
                System.out.println("001");
                super.v4();
                System.out.println("001");
            }
        };
    }
}
