package ru.sbt.averveyko.socialnetwork.userinterface;

import ru.sbt.averveyko.socialnetwork.friends.IFriendsStorage;
import ru.sbt.averveyko.socialnetwork.posts.IPostsStorage;
import ru.sbt.averveyko.socialnetwork.posts.Post;
import ru.sbt.averveyko.socialnetwork.users.IUsersStorage;
import ru.sbt.averveyko.socialnetwork.users.User;

import java.util.ArrayList;
import java.util.List;

public class StorageHelper {
    private final IUsersStorage usersStorage;
    private final IPostsStorage postsStorage;
    private final IFriendsStorage friendsStorage;

    public StorageHelper(IUsersStorage usersStorage, IPostsStorage postsStorage, IFriendsStorage friendsStorage) {
        this.usersStorage = usersStorage;
        this.postsStorage = postsStorage;
        this.friendsStorage = friendsStorage;
    }

    /**
     * Получить профиль пользователя
     * @param userid id пользователя
     */
    User getProfile(long userid){
        return usersStorage.getUser(userid);
    }

    /**
     * Получить "стену" пользователя (посты пользователя)
     * @param userid id пользователя
     */
    List<Post> getWall(long userid) {
        return postsStorage.getPosts(userid);
    }

    /**
     * Получить список друзей пользователя
     * @param userid id пользователя
     */
    List<User> getFriends(long userid) {
        return friendsStorage.getFriends(userid);
    }

    /**
     * Получить новости пользователя (посты друзей за последнее время)
     * @param userid id пользователя
     */
    List<Post> getFeed(long userid) {
        List<Post> feed = new ArrayList<Post>();
        for (User friend : friendsStorage.getFriends(userid)) {
            //Для каждого друга, получить все посты
            //(в идеале тут выбор нескольких последних постов)
            feed.addAll(postsStorage.getPosts(friend.getId()));
        }
        return feed;
    }
}
