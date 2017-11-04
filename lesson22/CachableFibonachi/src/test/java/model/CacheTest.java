package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CacheTest {
    Cache cache = new Cache();

    @Test
    public void putAndGet() throws Exception {
        cache.put(1, 1);
        assertEquals(cache.get(1), 1);

        cache.put(20, 6_765);
        assertEquals(cache.get(20), 6_765);

        cache.put(46, 1_836_311_903);
        assertEquals(cache.get(46), 1_836_311_903);

    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentSmall() throws Exception {
        cache.put(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentBig() throws Exception {
        cache.put(47, 0);
    }

}