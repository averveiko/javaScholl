package ru.sbt.averveyko.JMSChat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import javax.jms.*;

/**
 * Класс, отправляющий сообщения (Singleton)
 */
@Service
public class MessageProducerService {
    private final ConnectionFactory connectionFactory;
    private final Topic topic;

    private Connection connection;
    private Session session;
    private MessageProducer producer;

    @Autowired
    public MessageProducerService(ConnectionFactory connectionFactory, Topic topic) {
        this.connectionFactory = connectionFactory;
        this.topic = topic;

        try {
            this.connection = connectionFactory.createConnection();
            this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            this.producer = session.createProducer(topic);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void send(String message) {
        try {
            Message jmsMessage = session.createTextMessage(message);
            producer.send(jmsMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        System.out.println("desroy producer");
        try {
            if (this.connection != null) this.connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
