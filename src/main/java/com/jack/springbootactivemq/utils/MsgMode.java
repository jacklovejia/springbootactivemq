package com.jack.springbootactivemq.utils;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.Destination;

public class MsgMode {

    public static Destination getActiveMqQueue(String queueName) {
        return new ActiveMQQueue(queueName);
    }

    public static Destination getActiveMqTopic(String topicName) {
        return new ActiveMQTopic(topicName);
    }
}
