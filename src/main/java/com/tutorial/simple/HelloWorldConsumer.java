package com.tutorial.simple;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class HelloWorldConsumer
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

        MessageConsumer consumer = session.createConsumer(destination);

        //Wait for message
        Message message = consumer.receive(1000);

        if (message instanceof TextMessage){
            TextMessage textMessage = (TextMessage)message;
            String text = textMessage.getText();
            System.out.println("Recieved : "+text);
        }else{
            System.out.println("Recieved : "+message);
        }

        consumer.close();
        session.close();
        connection.close();

    }
}
