package ru.sbt.averveyko.socialnetwork.userinterface;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import ru.sbt.averveyko.socialnetwork.friends.IFriendsStorage;
import ru.sbt.averveyko.socialnetwork.posts.IPostsStorage;
import ru.sbt.averveyko.socialnetwork.posts.Post;
import ru.sbt.averveyko.socialnetwork.users.Gender;
import ru.sbt.averveyko.socialnetwork.users.IUsersStorage;
import ru.sbt.averveyko.socialnetwork.users.User;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StorageHelperTest {

    private StorageHelper storageHelper;

    private IUsersStorage usersStorage;
    private IPostsStorage postsStorage;
    private IFriendsStorage frendsStorage;

    @Before
    public void setUp() throws Exception {
        this.usersStorage = mock(IUsersStorage.class);
        this.postsStorage = mock(IPostsStorage.class);
        this.frendsStorage = mock(IFriendsStorage.class);

        //Create users
        User user0 = new User(
                "Bender Bending Rodriguez",
                4,
                Gender.MALE,
                "Сгибающий модуль 22 серийный номер 2716057"
        );

        User user1 = new User(
                "Doctor John Zoidberg",
                77,
                Gender.MALE,
                "Зойдберг — человеко-омароподобный инопланетянин с планеты Декапод-10"
        );

        User user2 = new User(
                "Turanga Leela",
                30,
                Gender.FEMALE,
                "Кпитан и пилот космического корабля курьерской фирмы «Planet Express»"
        );

        when(usersStorage.getUser(0)).thenReturn(user0);
        when(usersStorage.getUser(1)).thenReturn(user1);
        when(usersStorage.getUser(2)).thenReturn(user2);

        //Create posts
        List<Post> posts0 = new ArrayList<>();
        List<Post> posts1 = new ArrayList<>();
        List<Post> posts2 = new ArrayList<>();

        Post post0 = new Post(0,"Test post by Bender", "Test content");
        Post post1 = new Post(1,"Test post by Zoidberg", "Test content");
        Post post2 = new Post(2,"Test post by Leela", "Test content");

        posts0.add(post0);
        posts1.add(post1);
        posts2.add(post2);

        when(postsStorage.getPosts(0)).thenReturn(posts0);
        when(postsStorage.getPosts(1)).thenReturn(posts1);
        when(postsStorage.getPosts(2)).thenReturn(posts2);

        //Create friends
        List<User> friends = new ArrayList<>();
        friends.add(user1);
        friends.add(user2);

        when(frendsStorage.getFriends(0)).thenReturn(friends);

        this.storageHelper = new StorageHelper(usersStorage, postsStorage, frendsStorage);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getProfile() throws Exception {
        User user = storageHelper.getProfile(0);

        assertEquals(user.getName(), "Bender Bending Rodriguez");
        assertEquals(user.getAge(), 4);
        assertEquals(user.getGender(), Gender.MALE);
        assertEquals(user.getInfo(), "Сгибающий модуль 22 серийный номер 2716057");
    }

    @Test
    public void getWall() throws Exception {
        List<Post> posts = storageHelper.getWall(0);

        assertEquals(posts.get(0).getUserid(), 0);
        assertEquals(posts.get(0).getTitle(), "Test post by Bender");
        assertEquals(posts.get(0).getContent(), "Test content");
    }

    @Test
    public void getFriends() throws Exception {
        List<User> friends = storageHelper.getFriends(0);

        assertEquals(friends.get(0),storageHelper.getProfile(1));
        assertEquals(friends.get(1),storageHelper.getProfile(2));
    }

    @Test
    public void getFeed() throws Exception {
        List<Post> feed = storageHelper.getFeed(0);

        assertEquals(feed.get(0).getTitle(), "Test post by Zoidberg" );
        assertEquals(feed.get(1).getTitle(), "Test post by Leela");
    }

}