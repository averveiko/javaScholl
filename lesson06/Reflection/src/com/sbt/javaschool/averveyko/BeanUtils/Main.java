package com.sbt.javaschool.averveyko.BeanUtils;

public class Main {

    public static void main(String[] args) {
        Employee from = new Employee("Alex", 32, "Programmer");
        Person to = new Person("Noname", 0);

        System.out.println("\nСвойства класса получателя до assign: ");
        System.out.println("name: "  + to.getName());
        System.out.println("age: "  + to.getAge());

        BeanUtils.assign(to, from);

        System.out.println("\nСвойства класса получателя после assign: ");
        System.out.println("name: "  + to.getName());
        System.out.println("age: "  + to.getAge());
    }

}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

    private String privateMethod() {
        return "This is private method!";
    }
}

class Employee extends Person {
    private String profession;

    public Employee(String name, int age, String profession) {
        super(name, age);
        this.profession = profession;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}

