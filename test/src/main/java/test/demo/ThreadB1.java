package test.demo;

/**
 * @author
 * @date 2022 年 02 月 28 日
 */
public class ThreadB1 extends  Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(1000L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        Thread currentThread = Thread.currentThread();
        String curThreadName = currentThread.getName();
        System.out.println("这是线程的名称"+currentThread.getName());
        System.out.println("返回当前线程"+curThreadName+" “线程组中活动的线程数目"+Thread.activeCount());
        System.out.println("返回该线程"+curThreadName+"的标识符"+currentThread.getId());

        A a = new A();
        a.test();
        /*try {
            a.wait(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println(":优先级"+currentThread.getPriority());
        System.out.println("贤臣的线程组"+currentThread.getThreadGroup());
        System.out.println("转太"+currentThread.getState());
        System.out.println("是否处于活动状态"+currentThread.isAlive());
        System.out.println("是否是守护线程"+currentThread.isDaemon());


    }
}
