package test.demo.exatant;

/**
 * @author
 * @date 2022 年 03 月 03 日
 */
public class B extends A{
    @Override
    void test1() {
        super.test2();
        System.out.println("test1B");
    }

    @Override
    void test2() {
        super.test2();
        System.out.println("test2B");
    }
}
