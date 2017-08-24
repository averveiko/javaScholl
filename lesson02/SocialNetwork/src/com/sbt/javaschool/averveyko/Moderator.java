package com.sbt.javaschool.averveyko;

public class Moderator extends BaseUser implements Monetizable {

    public Moderator(String nickname, int age) {
        super(nickname, age);
    }

    @Override
    protected String toJSON() {
        return "{\n" +
                "\t\"type\": \"" + this.getClass().getName() + "\",\n" +
                "\t\"id\": " + id + ",\n" +
                "\t\"nickname\": " + "\""+ nickname + "\",\n" +
                "\t\"age\": " + age + "\n" +
                "}";
    }

    @Override
    public String toString() {
        return "Moderator{" +
                "id=" + id +
                '}';
    }

    @Override
    public String monetize() {
        //Модерировать посты и банить
        return "модерирует " + this.toString();
    }
}
