package ru.sbt.averveyko.socialnetwork.friends;

import ru.sbt.averveyko.socialnetwork.users.User;

import java.util.List;

public interface IFriendsStorage {
    List<User> getFriends(long userid);
}
