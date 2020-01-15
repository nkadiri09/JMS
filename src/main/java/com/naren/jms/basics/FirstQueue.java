package com.naren.jms.basics;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class FirstQueue {

    public static void main(String[] args)  {
        InitialContext initialContext= null;
        try{
        initialContext= new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession();
            Queue queue = (Queue) initialContext.lookup("queue/myQueue");
            MessageProducer producer = session.createProducer(queue);
            TextMessage message = session.createTextMessage("hello JMS world!");
            producer.send(message);

        } catch (NamingException e){
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
