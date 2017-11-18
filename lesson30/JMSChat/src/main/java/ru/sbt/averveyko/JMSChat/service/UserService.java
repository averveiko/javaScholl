package ru.sbt.averveyko.JMSChat.service;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Сервис, ведущий учет пользователей чата
 */
@Service
public class UserService {
    private final Set<String> users = new ConcurrentSkipListSet<>();

    public boolean contains(String userName) {
        return users.contains(userName);
    }

    public void add(String userName) {
        users.add(userName);
    }
}
