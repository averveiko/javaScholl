package ru.sbt.averveyko.JMSChat.service;

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

    private final ConnectionFactory connectionFactory;
    private final Topic topic;

    private Connection connection;
    private Session session;
    private MessageConsumer messageConsumer;

    private List<String> messages = new ArrayList<>();

    @Autowired
    public MessageConsumerService(ConnectionFactory connectionFactory, Topic topic) {
        this.connectionFactory = connectionFactory;
        this.topic = topic;

        try {
            this.connection = connectionFactory.createConnection();
            this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            this.messageConsumer = session.createConsumer(topic);

            messageConsumer.setMessageListener(this);

            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
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
            // TODO Debug
            System.out.println(textMessage.getText());
        } catch (JMSException e) {
            System.err.println("Error while reading message");
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            if (this.connection != null) this.connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
