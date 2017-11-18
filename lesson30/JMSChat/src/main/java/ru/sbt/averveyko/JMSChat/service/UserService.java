package ru.sbt.averveyko.JMSChat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Сервис, управляющий пользователями чата
 */
@Service
public class UserService {
    private final ApplicationContext context;
    private final Map<String, MessageConsumerService> users = new ConcurrentHashMap<>();

    @Autowired
    public UserService(ApplicationContext context) {
        this.context = context;
    }

    public boolean contains(String userName) {
        return users.keySet().contains(userName);
    }

    public void add(String userName) {
        MessageConsumerService consumerService = context.getBean(MessageConsumerService.class);

        users.put(userName, consumerService);
    }

    public List<String> getMessages(String username) {
        return users.get(username).getMessages();
    }
}
