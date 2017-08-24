package com.sbt.javaschool.averveyko;

abstract public class BaseUser {
    static long idCounter; //Для генерации последовательных ID

    protected long id;
    protected String nickname;
    protected int age;

    BaseUser(String nickname, int age) {
        this.id = idCounter++;
        this.nickname = nickname;
        this.age = age;
    }

    protected void like(long photoId) { //Способность "лайкать" дана всем потомкам
        //Лайкаем фотку
    }

    protected abstract String toJSON(); //Будет реализовано в потомках

    public String toString() {
        return "BaseUser{" +
                "id=" + id +
                '}';
    }

}
