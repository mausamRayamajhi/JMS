package com.tutorial.simple;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Hello world!
 *
 */
public class HelloWorldProducer
{
    public static void main( String[] args ) throws Exception {
        /**
         * Creating a session factory
         */
        ActiveMQConnectionFactory connectionFactory =  new ActiveMQConnectionFactory("tcp://DESKTOP-UUL4TBJ:61616");

        /**
         * Create a connection
         */
        Connection connection = connectionFactory.createConnection();
        connection.start();

        /**
         * Create a session
         */
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        /**
         * Create a Destionation (queue / topic )
         */
        Destination destination = session.createQueue("HELLOWORLD.TESTQ");

        /**
         * Create MessageProducer from session for (topic / queue )
         */
        MessageProducer producer  = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        /**
         * Create message
         */
        String text = "Hello world! from : "+ Thread.currentThread().getName();
        TextMessage message = session.createTextMessage(text);

        //Tell prodecure to send message
        System.out.println("Sent message "+message.hashCode() + " : "+Thread.currentThread().getName());
        producer.send(message);

        session.close();
        connection.close();
    }
}
