package ru.sbt.averveyko.socialnetwork.posts;

public class Post {
    private static long idCounter;
    private final long id;
    private final long userid;
    private String title;
    private String content;

    public Post(long userid) {
        this.id = this.idCounter++;
        this.userid = userid;
    }

    public Post(long userid, String title, String content) {
        this(userid);
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public long getUserid() {
        return userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
