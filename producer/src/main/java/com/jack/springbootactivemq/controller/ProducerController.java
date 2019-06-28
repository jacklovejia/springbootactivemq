package com.jack.springbootactivemq.controller;

import com.jack.springbootactivemq.utils.MsgMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/msg")
public class ProducerController {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("/test01")
    public String test01() {
        for (int i = 0; i < 100; i++) {
            String msg = "订单号:"+i;
            String queueName = "order";
            jmsMessagingTemplate.convertAndSend(MsgMode.getActiveMqQueue(queueName), msg);
        }
        return "消息已发送";
    }

    @RequestMapping("/test02/{msg}")
    public String test02(@PathVariable String msg) {
        String topName = "notify";
        jmsMessagingTemplate.convertAndSend(MsgMode.getActiveMqTopic(topName), msg);
        return "消息已发送";
    }

}
