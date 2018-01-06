package ru.sbt.averveyko.jmschat.service;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import javax.jms.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, читающий сообщения из топика (prototype, по одному на каждого пользователя)
 */
@Service
@Scope("prototype")
public class MessageConsumerService implements MessageListener {
    private static final Logger logger = LogManager.getLogger("MessageConsumerService");

    private Connection connection;
    private Session session;
    private MessageConsumer messageConsumer;

    private List<String> messages = new ArrayList<>();

    @Autowired
    public MessageConsumerService(ConnectionFactory connectionFactory, Topic topic) {
        try {
            this.connection = connectionFactory.createConnection();
            this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            this.messageConsumer = session.createConsumer(topic);

            messageConsumer.setMessageListener(this);

            connection.start();
        } catch (JMSException e) {
            logger.info("Can't create MessageConsumerService ", e);
        }
    }

    public List<String> getMessages() {
        List<String> messageList = new ArrayList<>(messages);
        messages.clear();
        return messageList;
    }

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            messages.add(textMessage.getText());
        } catch (JMSException e) {
            logger.info("Can't read message ", e);
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            if (this.connection != null) this.connection.close();
        } catch (JMSException e) {
            logger.info("Can't destroy MessageConsumerService ", e);
        }
    }
}
