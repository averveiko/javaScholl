package ru.sbt.averveyko.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Рик", 65, Gender.MALE));
        personList.add(new Person("Морти", 15, Gender.MALE));
        personList.add(new Person("Бэт", 33, Gender.FEMALE));
        personList.add(new Person("Саммер", 35, Gender.FEMALE));
        personList.add(new Person("Джерри", 17, Gender.MALE));

        Map<String, CartoonPerson> map = Streams.of(personList)
            .filter(p -> p.getAge() > 30)
            .transform(p -> new CartoonPerson(p.getName(),p.getAge() + 5, p.getGender(),"Рик и Морти"))
            .toMap(CartoonPerson::getName, p -> p);

        for (String s : map.keySet()) {
            System.out.println("Key: " + s);
            System.out.println(map.get(s));
        }
    }
}
