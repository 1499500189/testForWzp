package com.wzp.test.rocketmqtest;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @author
 * @date 2022 年 03 月 08 日
 */
public class SomeConsumer {
    public static void main(String[] args) {
        xiaofeizhe();
    }
    public  static  void  xiaofeizhe (){
        //定义一个消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("cg");
                   consumer.setNamesrvAddr("localhost:9876");
                   consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        try {
            //指定消费topic与tag
            consumer.subscribe("someTopic","*");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        //可以设置广播模式消费或者集群模式消费  ，默认是集群消费
       // consumer.setMessageModel(MessageModel.BROADCASTING);

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt msg :msgs){
                    System.out.println(msg);
                    System.out.println("消费消息");
                }
               return  ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //开启消费者消费
        try {
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        System.out.println("Consumer Started");

    }
}
