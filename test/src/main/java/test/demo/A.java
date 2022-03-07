package test.demo;

/**
 * @author
 * @date 2022 年 02 月 28 日
 */
public  class  A  {


    public synchronized  static void test(){
        Thread thread = Thread.currentThread();
        System.out.println("获取到锁test1"+thread);

        System.out.println("使用A方法的是"+thread);

        try {
            Thread.sleep(1000000L);
            //wait();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        System.out.println("执行完成"+thread);
    }
   /* public synchronized  void test2(){
        System.out.println("获取到锁test2");
         this.notifyAll();
        System.out.println("执行完成test2");
    }*/
}
