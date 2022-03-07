package test.demo;

/**
 * @author
 * @date 2022 年 02 月 28 日
 */
public class ThreadA extends   Thread{
    @Override
    public void run() throws  RuntimeException {
        Thread currentThread = Thread.currentThread();
        System.out.println("这是线程的名称"+currentThread.getName());
 try {
     System.out.println("执行睡眠等待");
     Thread.sleep(10000L);
 }catch (Exception e){
   e.printStackTrace();
 }
        A a = new A();
        a.test();

        System.out.println("a执行最后 ，    中断获取锁 ");

    }
}
