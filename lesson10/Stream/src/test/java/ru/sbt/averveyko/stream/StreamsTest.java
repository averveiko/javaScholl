package ru.sbt.averveyko.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class StreamsTest {

    private List<Person> personList;

    @Before
    public void setUp() throws Exception {
        personList = new ArrayList<>();
        personList.add(new Person("Рик", 65, Gender.MALE));
        personList.add(new Person("Морти", 15, Gender.MALE));
        personList.add(new Person("Бэт", 33, Gender.FEMALE));
        personList.add(new Person("Саммер", 35, Gender.FEMALE));
        personList.add(new Person("Джерри", 17, Gender.MALE));
    }

    @Test
    public void filter() throws Exception {
        Streams<Person> stream = Streams
                .of(this.personList)
                .filter(p -> p.getAge() > 30);
        assertEquals(stream.size(), 3);
    }

    @Test
    public void toMap() throws Exception {
        Map<String, CartoonPerson> map = Streams.of(personList)
                .filter(p -> p.getAge() > 30)
                .transform(p -> new CartoonPerson(p.getName(),p.getAge() + 5, p.getGender(),"Рик и Морти"))
                .toMap(CartoonPerson::getName, p -> p);
        assertEquals(map.size(),3);
        assertTrue(map.containsKey("Рик"));
        assertTrue(map.containsKey("Бэт"));
        assertTrue(map.containsKey("Саммер"));

        assertEquals(map.get("Рик").getName(), "Рик");
    }
}