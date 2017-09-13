package ru.sbt.averveyko.socialnetwork.posts;

import java.util.List;

public interface IPostsStorage {
    List<Post> getPosts(long userid);
    void putPost(Post post);
}
