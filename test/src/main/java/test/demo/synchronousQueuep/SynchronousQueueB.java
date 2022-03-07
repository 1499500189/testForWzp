package test.demo.synchronousQueuep;

import java.util.concurrent.SynchronousQueue;

/**
 * @author
 * @date 2022 年 03 月 01 日
 */
public class SynchronousQueueB implements  Runnable{

    private SynchronousQueue<String>   a ;


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println("生产");
        try {
             a.put("dsasd");
             a.put("sdac");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public SynchronousQueueB(SynchronousQueue<String> a) {

        this.a = a;
    }
}
