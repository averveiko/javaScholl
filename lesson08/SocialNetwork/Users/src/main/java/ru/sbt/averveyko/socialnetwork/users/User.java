package ru.sbt.averveyko.socialnetwork.users;

public class User {
    private static long idCounter;

    private final long id;
    private String name;
    private int age;
    private Gender gender;
    private String info;

    public User() {
        this.id = idCounter++;
    }

    public User(String name, int age, Gender gender, String info) {
        this();
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.info = info;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", info='" + info + '\'' +
                '}';
    }
}
