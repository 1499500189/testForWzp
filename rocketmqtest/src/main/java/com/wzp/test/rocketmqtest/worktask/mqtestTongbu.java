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
public class mqtestTongbu {
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
        List<WorkTaskThread> tasks = new ArrayList<WorkTaskThread>();

        DefaultMQProducer producer = new DefaultMQProducer("pg");
        try {
            producer.start();
            producer.setNamesrvAddr("localhost:9876");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        List<Future<Long>> futures = null;
        try {

            for (int i = 0; i < corePoolSize; i++) {
                WorkTaskThread workTaskThread = new WorkTaskThread(producer);
                tasks.add(workTaskThread);
            }
            futures= exector.invokeAll(tasks);
            System.out.println("执行等待"+(System.currentTimeMillis()-start));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.getStackTrace();
        }
        // 关闭producer
        producer.shutdown();

    }

    private static class WorkTaskThread implements Callable<Long> {
        private    DefaultMQProducer producer;

        public WorkTaskThread(DefaultMQProducer producer) {
            this.producer = producer;
        }

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Long call() throws Exception {
       //DefaultMQProducer producer = new DefaultMQProducer("pg");

        try {
            //发送100条消息
            for (int i = 0; i <10000 ; i++) {
                byte[] body =("hi,+"+i).getBytes();
                Message message = new Message("someTopic", "someTage", body);
                try {
                    SendResult sendResult = producer.send(message);
                    System.out.println(sendResult);
                } catch (RemotingException e) {
                    e.printStackTrace();
                } catch (MQBrokerException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        } catch (MQClientException e) {
            e.printStackTrace();
        }

            return 1L;
        }
    }

}
