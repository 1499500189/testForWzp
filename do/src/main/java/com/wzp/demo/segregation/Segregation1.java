package com.wzp.demo.segregation;

/**
 * @author
 * @date 2021 年 10 月 29 日
 */
public interface Segregation1 {

    void operation1();
    void operation2();
    void operation3();
    void operation4();
    void operation5();
}

class Segregation1Op implements  Segregation1{


    @Override
    public void operation1() {


        System.out.println("实现了第一个方法");
    }

    @Override
    public void operation2() {
        System.out.println("实现了第二、" +
                "个方法");
    }

    @Override
    public void operation3() {
        System.out.println("实现了第三" +
                "个方法");
    }

    @Override
    public void operation4() {
        System.out.println("实现了第四个方法");
    }

    @Override
    public void operation5() {
        System.out.println("实现了第五个方法");
    }
}
class Segregation2Op implements  Segregation1{


    @Override
    public void operation1() {


        System.out.println("实现了第一个方法    22");
    }

    @Override
    public void operation2() {
        System.out.println("实现了第二、" +
                "个方法    22");
    }

    @Override
    public void operation3() {
        System.out.println("实现了第三" +
                "个方法    22");
    }

    @Override
    public void operation4() {
        System.out.println("实现了第四个方法 22");
    }

    @Override
    public void operation5() {
        System.out.println("实现了第五个方法    22");
    }
}
class  A {
    public  void depend1(Segregation1 interface1){
        interface1.operation1();
    }
    public  void depend2(Segregation1 segregation1)
    {
        segregation1.operation2();
    }

}
class  B{
    public  void depend2(Segregation1 segregation1){
        segregation1.operation1();
    }

    public static void main(String[] args) {
        B b = new B();
        Segregation2Op segregation2Op = new Segregation2Op();
        b.depend2(segregation2Op);
    }
}
