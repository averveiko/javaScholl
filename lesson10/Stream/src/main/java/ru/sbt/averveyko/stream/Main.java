package ru.sbt.averveyko.stream;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {


        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Рик", 65, Gender.MALE));
        personList.add(new Person("Морти", 15, Gender.MALE));
        personList.add(new Person("Бэт", 33, Gender.FEMALE));
        personList.add(new Person("Саммер", 35, Gender.FEMALE));
        personList.add(new Person("Джерри", 17, Gender.MALE));

        personList.stream().filter()


        Streams<Person> stream = Streams.of(personList);
        Streams<Person> newstream = stream.filter((Person p) -> p.getAge() > 30);
        System.out.println("end");
        //java.util.stream.Stream;
    }
}
