package com.wzp.test.rocketmqtest.worktask;

import com.wzp.test.rocketmqtest.general.ICBCTransactionListener;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author
 * @date 2022 年 03 月 09 日
 */
public class TransactionProducer {
    public static void main(String[] args) throws Exception {
        long start = new Date().getTime();
        TransactionMQProducer producer = new
                TransactionMQProducer("tpg");
        producer.setNamesrvAddr("localhost:9876");
        /**
         * 定义一个线程池
         * @param corePoolSize 线程池中核心线程数量
         * @param maximumPoolSize 线程池中最多线程数
         * @param keepAliveTime 这是一个时间。当线程池中线程数量大于核心线程数量
        是，
         *                     多余空闲线程的存活时长
         * @param unit 时间单位
         * @param workQueue 临时存放任务的队列，其参数就是队列的长度
         * @param threadFactory 线程工厂
         */
        ExecutorService executorService = new ThreadPoolExecutor(6, 10,
                100, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2000), new
                ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("1thread");
                        return thread;
                    }
                });
        // 为生产者指定一个线程池 ,主要是用于回调的时候和消息发送进行多线程操作
        producer.setExecutorService(executorService);
        // 为生产者添加事务监听器
        producer.setTransactionListener(new ICBCTransactionListener());
        producer.start();
        String[] tags = {"TAGA","TAGB","TAGC"};
        for (int i = 0; i <100000; i++) {
            byte[] body = ("Hi," + i).getBytes();
            Message msg = new Message("TTopic", tags[0], body);
            // 发送事务消息
            // 第二个参数用于指定在执行本地事务时要使用的业务参数
            SendResult sendResult =
                    producer.sendMessageInTransaction(msg,null);
            System.out.println("发送结果为：" +
                    sendResult.getSendStatus());
        }
        long endTime = new Date().getTime();
        System.out.println("时间差===>"+(endTime-start));
    }
}
