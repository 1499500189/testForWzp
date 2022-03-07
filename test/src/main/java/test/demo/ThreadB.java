package test.demo;

/**
 * @author
 * @date 2022 年 02 月 28 日
 */
public class ThreadB implements  Runnable{
    @Override
    public void run() {

        Thread currentThread = Thread.currentThread();
        String curThreadName = currentThread.getName();
        System.out.println("这是线程的名称"+currentThread.getName());


        A a = new A();
        a.test();



    }

}
