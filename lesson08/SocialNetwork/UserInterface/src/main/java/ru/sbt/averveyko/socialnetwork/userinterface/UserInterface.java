package ru.sbt.averveyko.socialnetwork.userinterface;

import ru.sbt.averveyko.socialnetwork.friends.IFriendsStorage;
import ru.sbt.averveyko.socialnetwork.posts.IPostsStorage;
import ru.sbt.averveyko.socialnetwork.posts.Post;
import ru.sbt.averveyko.socialnetwork.users.IUsersStorage;
import ru.sbt.averveyko.socialnetwork.users.User;

public class UserInterface {
    private final IUsersStorage usersStorage;
    private final IPostsStorage postsStorage;
    private final IFriendsStorage friendsStorage;

    public UserInterface(IUsersStorage usersStorage, IPostsStorage postsStorage, IFriendsStorage friendsStorage) {
        this.usersStorage = usersStorage;
        this.postsStorage = postsStorage;
        this.friendsStorage = friendsStorage;
    }

    /**
     * Показать профиль пользователя
     * @param userid id пользователя
     */
    void showProfile(long userid){
        System.out.println("\n Профиль пользователя" + userid);
        System.out.println(usersStorage.getUser(userid));
    }

    /**
     * Показать "стену" пользователя (посты пользователя)
     * @param userid id пользователя
     */
    void showWall(long userid) {
        System.out.println("\n Стена пользователя" + userid);
        for (Post post : postsStorage.getPosts(userid)) {
            System.out.println(post);
        }
    }

    /**
     * Показать список друзей пользователя
     * @param userid id пользователя
     */
    void showFriends(long userid) {
        System.out.println("\n Друзья пользователя" + userid);
        for (User friend : friendsStorage.getFriends(userid)) {
            System.out.println(friend);
        }
    }

    /**
     * Показать новости пользователя (посты друзей за последнее время)
     * @param userid id пользователя
     */
    void showFeed(long userid) {
        System.out.println("\n Новости пользователя" + userid);
        for (User friend : friendsStorage.getFriends(userid)) {
            for (Post post : postsStorage.getPosts(friend.getId())) {
                //Для каждого друга, получить все посты, вывести
                //(в идеале тут хронологическая сортировка и все такое)
                System.out.println(post);
            }
        }
    }
}
