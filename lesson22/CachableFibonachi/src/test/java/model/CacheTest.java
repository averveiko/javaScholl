package model;

import dao.h2.H2CacheDao;
import org.junit.Test;

import static org.junit.Assert.*;

public class CacheTest {
    Cache cache = new Cache(new H2CacheDao("jdbc:h2:file:~/test.db"));

    @Test
    public void putAndGet() throws Exception {
        cache.put(1, 1, true);
        assertEquals(cache.get(1), Integer.valueOf(1));

        cache.put(20, 6_765, true);
        assertEquals(cache.get(20), Integer.valueOf(6_765));

        cache.put(46, 1_836_311_903, true);
        assertEquals(cache.get(46), Integer.valueOf(1_836_311_903));
    }
}