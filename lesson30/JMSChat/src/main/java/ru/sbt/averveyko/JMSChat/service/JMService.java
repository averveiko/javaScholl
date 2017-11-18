package ru.sbt.averveyko.JMSChat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Service
public class JMService {
    private final ConnectionFactory connectionFactory;
    private final Topic topic;

    @Autowired
    public JMService(ConnectionFactory connectionFactory, Topic topic) {
        this.connectionFactory = connectionFactory;
        this.topic = topic;
    }

    public void testJM() {
        try(Connection connection = connectionFactory.createConnection()){
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(topic);

            Message message = session.createTextMessage("Sashkam test message");
            producer.send(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


}
