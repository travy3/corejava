package com.zyu.corejava.mq;


import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by chenjie on 2016/2/25.
 */
public class Receiver {

    public static void main(String[] args){

        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session;
        Destination destination;
        // 消费者，消息接收者
        MessageConsumer consumer;

        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,"tcp://localhost:61616");

        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("FirstQueue");
            consumer = session.createConsumer(destination);
            while(true){
                TextMessage message = (TextMessage) consumer.receive(100000);
                if(null != message){
                    System.out.println("收到消息" + message.getText());
                }else break;

            }
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if(null != connection){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
