package com.jack.springbootactivemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

/**
 * 查了一些资料, 在整合springboot 有个人说springboot 整合 activeMQ 的时候,
 * 消息的 主题模式 和 队列模式 不能同时存在,
 *  topic和queue不能同时使用,
 *  springboot 控制使用topic 还是 queue 是通过配置 pub-sub-domain 属性实现的
 *  以下 是 通过配置 pub-sub-domain 的切换来配置出不同模式的listener
 */
@Configuration
public class MsgModeConfig {
    /**
     * pub-sub-domain=true 是主题模式
     * topic模式的ListenerContainer
     */
    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        ActiveMQConnectionFactory connectionFactor=new ActiveMQConnectionFactory();
        factory.setPubSubDomain(true);
        factory.setConnectionFactory(connectionFactor);
        return factory;
    }

    /**
     * pub-sub-domain=false (默认的) 是队列模式
     * queue模式的ListenerContainer
     */
    @Bean
    public JmsListenerContainerFactory<?> queueListenerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        ActiveMQConnectionFactory connectionFactor=new ActiveMQConnectionFactory();
        factory.setPubSubDomain(false);
        factory.setConnectionFactory(connectionFactor);
        return factory;
    }
}
