package com.wzp.test.rocketmqtest;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.concurrent.TimeUnit;

/**
 * @author
 * @date 2022 年 03 月 08 日
 */
public class Producer {
    public static void main(String[] args) {
        tongbu();
        //yibu();
       /* DefaultMQProducer producer = new DefaultMQProducer("pg");
        producer.setNamesrvAddr("localhost:9876");
        try {
            producer.start();


            //发送100条消息
            for (int i = 0; i <100 ; i++) {
                byte[] body =("hi,+"+i).getBytes();
                Message message = new Message("someTopic", "someTage", body);
                try {
                    SendResult  sendResult = producer.send(message);
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
        producer.shutdown();*/
    }
   public static  void  tongbu (){  //同步方式
       DefaultMQProducer producer = new DefaultMQProducer("pg");
       producer.setNamesrvAddr("localhost:9876");
       try {

           producer.start();


           //发送100条消息
           for (int i = 0; i <100 ; i++) {
               byte[] body =("hi,+"+i).getBytes();
               Message message = new Message("someTopic", "someTage", body);
               try {
                   SendResult  sendResult = producer.send(message);
                //   System.out.println(sendResult);  logger 输出  打印
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
   }

    public static void  yibu(){   //异步方式
        DefaultMQProducer producer = new DefaultMQProducer("pg");
        producer.setNamesrvAddr("localhost:9876");
        try {
            producer.start();


            //发送100条消息
            for (int i = 0; i <100 ; i++) {
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
    }

    public static void  danxiangxiaoxi (){      //不需要返回值的方式  ， 效率最高 ， 所以它们的生产者不需要等待回执 ， 效率是最高的 。
        //但出现错误的情况下 ，  就不会有重试的机制
        DefaultMQProducer producer = new DefaultMQProducer("pg");
        producer.setNamesrvAddr("localhost:9876");
        try {
            producer.start();

            //发送100条消息
            for (int i = 0; i <100 ; i++) {
                byte[] body =("hi,+"+i).getBytes();
                Message message = new Message("someTopic", "someTage", body);
                try {
                    //单向发送消息
                   producer.sendOneway(message);
                } catch (RemotingException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        } catch (MQClientException e) {
            e.printStackTrace();
        }

        producer.shutdown();
    }
}
