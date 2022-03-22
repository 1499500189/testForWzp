package com.wzp.test.rocketmqtest.worktask;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.Date;

/**
 * @author
 * @date 2022 年 03 月 22 日
 */
public class mqtest {
    public static void main(String[] args) {
        long time = new Date().getTime();
        DefaultMQProducer producer = new DefaultMQProducer("pg");
        producer.setNamesrvAddr("localhost:9876");
        try {
            producer.start();
            //发送100条消息
            for (int i = 0; i <60000 ; i++) {
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
        // 关闭producer
        producer.shutdown();
        long time1 = new Date().getTime();
        System.out.println("使用的时间是====>"+(time1-time));


    }
}
