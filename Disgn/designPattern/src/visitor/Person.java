package visitor;

/**
 * @author
 * @date 2021 年 12 月 20 日
 */
public  abstract  class Person {
//提供一个方法，让访问者可以访问
    public  abstract  void accept(Action action);
}
