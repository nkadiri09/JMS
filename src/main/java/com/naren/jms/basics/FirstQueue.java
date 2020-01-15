package com.naren.jms.basics;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class FirstQueue {

    public static void main(String[] args)  {
        InitialContext initialContext= null;
        Connection connection =null;
        try{
            initialContext = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
            Session session = connection.createSession();
            Queue queue = (Queue) initialContext.lookup("queue/myQueue");
            MessageProducer producer = session.createProducer(queue);
            TextMessage message = session.createTextMessage("hello JMS world!");
            producer.send(message);
            System.out.println("Message sent: "+message.getText());

            MessageConsumer consumer = session.createConsumer(queue);
            connection.start();
            TextMessage messageReceived = (TextMessage) consumer.receive();
            System.out.println("Message Received: "+messageReceived.getText());

        } catch (NamingException e){
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if(initialContext!=null){
                try {
                    initialContext.close();
                }catch (NamingException e){
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
