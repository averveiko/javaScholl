package ru.sbt.averveyko.jmschat.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import javax.jms.*;

/**
 * Класс, отправляющий сообщения (Singleton)
 */
@Service
public class MessageProducerService {
    private static final Logger logger = LogManager.getLogger("MessageProducerService");

    private Connection connection;
    private Session session;
    private MessageProducer producer;

    @Autowired
    public MessageProducerService(ConnectionFactory connectionFactory, Topic topic) {
        try {
            this.connection = connectionFactory.createConnection();
            this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            this.producer = session.createProducer(topic);

        } catch (JMSException e) {
            logger.info("Can't create MessageProducerService ", e);
        }
    }

    public void send(String message) {
        try {
            Message jmsMessage = session.createTextMessage(message);
            producer.send(jmsMessage);
        } catch (JMSException e) {
            logger.info("Can't send message ", e);
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            if (this.connection != null) this.connection.close();
        } catch (JMSException e) {
            logger.info("Can't destroy MessageProducerService ", e);
        }
    }
}
