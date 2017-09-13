package ru.sbt.averveyko.socialnetwork.users;

public interface IUsersStorage {
    User getUser(long id);
    void putUser(User user);
}
