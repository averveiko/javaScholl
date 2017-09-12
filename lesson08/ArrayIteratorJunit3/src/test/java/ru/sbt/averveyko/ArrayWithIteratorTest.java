package ru.sbt.averveyko;

import junit.framework.TestCase;

public class ArrayWithIteratorTest extends TestCase {

    private static final int ARR_SIZE = 5;

    private ArrayWithIterator<String> myArray = new ArrayWithIterator<String>(ARR_SIZE);

    public void setUp() throws Exception {
        super.setUp();

        for (int i = 0; i < ARR_SIZE; i++) {
            myArray.put(i, "String " + i);
        }
    }

    public void tearDown() throws Exception {
        this.myArray = null;
    }

    public void testPut() throws Exception {
        myArray.put(1,"testPut");
        assertEquals(myArray.get(1), "testPut");
        myArray.put(1,"String 1");
    }

    public void testGet() throws Exception {
        assertEquals(myArray.get(0), "String 0");
        assertEquals(myArray.get(4), "String 4");
    }

    public void testIterator() throws Exception {
        int i = 0;
        for (String s : myArray) {
            assertEquals(s, "String " + (i++));
        }
    }
}