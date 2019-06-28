package com.jack.springbootactivemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 消费者
 */
@Component
public class Consumer {
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    @JmsListener(destination = "order", containerFactory = "queueListenerFactory")
    public void test01(String msg) {
        /**
         * 多线程消费
         */
        executorService.execute(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("应用服务器 2 收到消息" + msg);
        });
    }

    @JmsListener(destination = "jack", containerFactory = "queueListenerFactory")
    public void test(String msg) {
        System.out.println("收到消息" + msg);
    }

    @JmsListener(destination = "notify", containerFactory = "topicListenerFactory")
    public void test02(String msg) {
        System.out.println("收到消息" + msg);
    }
}
