package com.jack.springbootactivemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

/**
 * 查了一些资料, 在整合springboot 有个人说springboot 整合 activeMQ 的时候,
 * 消息的 主题模式 和 队列模式 不能同时存在,
 * topic和queue不能同时使用,
 * springboot 控制使用topic 还是 queue 是通过配置 pub-sub-domain 属性实现的
 * 以下 是 通过配置 pub-sub-domain 的切换来配置出不同模式的listener
 */
@Configuration
public class MsgModeConfig {

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        return new ActiveMQConnectionFactory();
    }
    /**
     * pub-sub-domain=true 是主题模式
     * topic模式的ListenerContainer
     */
    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        ActiveMQConnectionFactory activeMQConnectionFactor = new ActiveMQConnectionFactory();
        factory.setPubSubDomain(true);
        factory.setSessionTransacted(true);
        factory.setAutoStartup(true);
        //开启持久化订阅
        factory.setSubscriptionDurable(true);
        factory.setClientId("jack");
        factory.setConnectionFactory(activeMQConnectionFactory);
        return factory;
    }

    /**
     * pub-sub-domain=false (默认的) 是队列模式topicListenerFactory
     * queue模式的ListenerContainer
     */
    @Bean
    public JmsListenerContainerFactory<?> queueListenerFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        ActiveMQConnectionFactory connectionFactor = new ActiveMQConnectionFactory();
        factory.setPubSubDomain(false);
        factory.setConnectionFactory(activeMQConnectionFactory);
        return factory;
    }


    @Bean
    public JmsTemplate jmsTemplate(ActiveMQConnectionFactory activeMQConnectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setDeliveryMode(2);//进行持久化配置 1表示非持久化，2表示持久化
        jmsTemplate.setDeliveryPersistent(true);
        activeMQConnectionFactory.setTrustAllPackages(true);//使用实体直接入队时配置，字符串入队列，不需要，为了演示将整个实体件入队传输。
        jmsTemplate.setConnectionFactory(activeMQConnectionFactory);
        jmsTemplate.setSessionAcknowledgeMode(1);//客户端签收模式
        return jmsTemplate;
    }

}
