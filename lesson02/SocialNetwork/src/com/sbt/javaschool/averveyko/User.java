package com.sbt.javaschool.averveyko;

public class User extends BaseUser implements Monetizable{

    protected boolean isBanned;

    public User(String nickname, int age) {
        super(nickname, age);
        isBanned = false;
    }

    @Override
    protected String toJSON() {
        return "{\n" +
                "\t\"type\": \"" + this.getClass().getName() + "\",\n" +
                "\t\"id\": " + id + ",\n" +
                "\t\"nickname\": " + "\""+ nickname + "\",\n" +
                "\t\"age\": " + age + "\n" +
                "\t\"isBanned\": " + isBanned + "\n" +
                "}";
    }

    @Override
    public String monetize() {
        //Проанализировать лайки и показать юзеру рекламу
        return "show advertising " + this.toString();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }

    protected void ban(BaseUser baseuser) {
        if (baseuser instanceof Moderator) {    //Проверяем есть ли права на бан
            this.isBanned = true;
            System.out.println(this + " banned " + baseuser);
        } else {
            System.out.println(baseuser + " You do not have the right to ban!");
        }
    }


}
