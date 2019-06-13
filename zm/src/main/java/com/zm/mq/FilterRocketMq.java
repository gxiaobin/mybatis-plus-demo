package com.zm.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * @author 一个忙来无聊的人
 * @desc 过滤器
 * @date 2019/5/16 17:20
 *
 * 数值比较，如>，>=，<，<=，BETWEEN，=;
 * 字符比较，如=，<>，IN;
 * IS NULL或IS NOT NULL;
 * 逻辑AND，OR，NOT，
 * 常量类型是：
 *
 * 数字，如123,3.1415;
 * 字符，如'abc'，必须用单引号制作;
 * NULL，特殊常数;
 * 布尔值，TRUE或FALSE;
 *
 */
public class FilterRocketMq {

    /**
     * 发送消息
     * 可以在发送时通过方法将属性放入消息中putUserProperty
     *
     * @throws Exception
     */
    public void productMsg() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        producer.start();
        for (int i = 0; i < 10; i++) {
            Message msg = new Message("TopicTest", "tag", ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 发送时将属性房子消息中
            msg.putUserProperty("a", String.valueOf(i));
            SendResult sendResult = producer.send(msg);
        }
// Set some properties.


        producer.shutdown();

    }

    /**
     * 消费消息
     * 用于MessageSelector.bySql在使用时通过SQL92选择消息
     * @throws Exception
     */
    public void consumMsg() throws Exception {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name_4");

        // 通过sql 筛选消息    指定只能在 0-3之间
        // only subsribe messages have property a, also a >=0 and a <= 3
        consumer.subscribe("TopicTest", MessageSelector.bySql("a between 0 and 3"));

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }
}

