package com.wzp.test.rocketmqtest.worktask;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author
 * @date 2022 年 03 月 22 日
 */
public class mqtestYibu {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        //每个sheet保存的数据量
        int num = 100000;
        int corePoolSize = 6;
        int maximumPoolSize = 20;

        //用线程池管理多线程
        ThreadPoolExecutor exector = (ThreadPoolExecutor) Executors.newFixedThreadPool(corePoolSize);
        exector.setCorePoolSize(corePoolSize);
        exector.setMaximumPoolSize(maximumPoolSize);
        List<WorkTaskThreadYibu> tasks = new ArrayList<WorkTaskThreadYibu>();


        List<Future<Long>> futures = null;
        try {

            for (int i = 0; i < corePoolSize; i++) {
                WorkTaskThreadYibu workTaskThread = new WorkTaskThreadYibu();
                tasks.add(workTaskThread);
            }
            futures= exector.invokeAll(tasks);
            System.out.println("执行等待"+(System.currentTimeMillis()-start));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    private static class WorkTaskThreadYibu implements Callable<Long> {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Long call() throws Exception {
            DefaultMQProducer producer = new DefaultMQProducer("pg");
            producer.setNamesrvAddr("localhost:9876");
            try {
                producer.start();


                //发送100条消息
                for (int i = 0; i <10 ; i++) {
                    byte[] body =("hi,+"+i).getBytes();
                    Message message = new Message("someTopic", "someTage", body);
                    try {

                        //使用回调 ， 异步发送消息
                        producer.send(message, new SendCallback() {
                            @Override
                            public void onSuccess(SendResult sendResult) {
                                System.out.println("回调成功。"+sendResult);
                            }

                            @Override
                            public void onException(Throwable throwable) {

                                throwable.printStackTrace();
                            }
                        });
                    } catch (RemotingException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            } catch (MQClientException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            producer.shutdown();

            return 1L;
        }
    }
}
