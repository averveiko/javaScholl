package ru.sbt.averveyko.cacheproxy;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReflectionsUtilsTest {
    @Test
    public void getPrimitiveClass() throws Exception {
        //(Byte, Short, Integer, Long, Float, Double)
        assertEquals(ReflectionsUtils.GetPrimitiveClass(Byte.class), byte.class);
        assertEquals(ReflectionsUtils.GetPrimitiveClass(Short.class), short.class);
        assertEquals(ReflectionsUtils.GetPrimitiveClass(Integer.class), int.class);
        assertEquals(ReflectionsUtils.GetPrimitiveClass(Long.class), long.class);
        assertEquals(ReflectionsUtils.GetPrimitiveClass(Float.class), float.class);
        assertEquals(ReflectionsUtils.GetPrimitiveClass(Double.class), double.class);
    }
}