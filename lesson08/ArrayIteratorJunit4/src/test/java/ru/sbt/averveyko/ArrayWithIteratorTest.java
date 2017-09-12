package ru.sbt.averveyko;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayWithIteratorTest {
    private static final int ARR_SIZE = 10;
    ArrayWithIterator<String> array = new ArrayWithIterator<String>(ARR_SIZE);

    @Before
    public void before() throws Exception {
        for (int i = 0; i < ARR_SIZE; i++) {
            array.put(i, "String " + i);
        }
    }

    @After
    public void after() throws Exception {
        array = null;
    }

    @Test
    public void put() throws Exception {
        array.put(0, "Test");
        assertEquals(array.get(0), "Test");
        array.put(0, "String 0");
    }

    @Test
    public void get() throws Exception {
        assertEquals(array.get(0), "String 0");
        assertEquals(array.get(5), "String 5");
    }

    @Test
    public void iterator() throws Exception {
        int i = 0;
        for (String s : array) {
            assertEquals(s, "String " + (i++));
        }
    }

}