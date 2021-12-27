package test.demo.prototype;

/**
 * @author
 * @date 2021 年 12 月 02 日
 */
public class Client {
    public static void main(String[] args) {
        Sheep sheep = new Sheep("tom", 1, "白色");
        Sheep sheep1 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
        //使用原型模式去创建 ，，原型模式 每一次获取到的都不是相同的


    }
}
