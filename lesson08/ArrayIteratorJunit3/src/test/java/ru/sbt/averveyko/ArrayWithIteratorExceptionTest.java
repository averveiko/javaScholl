package ru.sbt.averveyko;

import junit.framework.TestCase;

import java.util.Iterator;

public class ArrayWithIteratorExceptionTest extends TestCase {

    ArrayWithIterator<Integer> array = new ArrayWithIterator<Integer>(0);

    public void testPut() throws Exception {
        try {
            array.put(1, 1);
            fail("Missing exception");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().length() > 0);
        }
    }

    public void testGet() throws Exception {
        try {
            array.get(1);
            fail("Missing exception");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().length() > 0);
        }
    }

    public void testIterator() throws Exception {
        Iterator iterator = array.iterator();
        try {
            iterator.remove();
            fail( "Missing exception" );
        } catch (UnsupportedOperationException e) {
            assertEquals("remove not supported", e.getMessage());
        }
    }
}