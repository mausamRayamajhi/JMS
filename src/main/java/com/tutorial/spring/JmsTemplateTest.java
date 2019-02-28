package com.tutorial.spring;


import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JmsTemplateTest
{
    public static void main(String[] arg) throws Exception
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("jsm-context.xml");
        JmsTemplate template = (JmsTemplate) ctx.getBean("jmsTemplate");

        template.send("SpringSendTestQueqe", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage();
                message.setText("This is a message from spring configuration.");
                return message;
            }
        });
        System.out.println("Message Sent");
        System.exit(1);
    }
}
