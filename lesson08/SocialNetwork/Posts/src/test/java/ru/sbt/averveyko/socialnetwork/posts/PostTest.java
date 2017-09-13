package ru.sbt.averveyko.socialnetwork.posts;

import org.junit.Test;

import static org.junit.Assert.*;

public class PostTest {
    Post post = new Post(1);
    @Test
    public void getsetTitle() throws Exception {
        post.setTitle("Test title");
        assertEquals(post.getTitle(), "Test title");
    }

    @Test
    public void getsetContent() throws Exception {
        post.setContent("Test content");
        assertEquals(post.getContent(), "Test content");
    }
}