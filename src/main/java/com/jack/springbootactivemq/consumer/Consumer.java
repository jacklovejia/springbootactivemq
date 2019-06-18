package com.jack.springbootactivemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
@Component
public class Consumer {
    @JmsListener(destination = "order",containerFactory = "queueListenerFactory")
    public void test01(String msg) {
        System.out.println("收到消息" + msg);
    }

    @JmsListener(destination = "notify", containerFactory = "topicListenerFactory")
    public void test02(String msg) {
        System.out.println("收到消息" + msg);
    }
}
