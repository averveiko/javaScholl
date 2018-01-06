package model;

import dao.ICacheDao;

import java.util.HashMap;
import java.util.Map;

public class Cache implements ICache {
    private Map<Integer, Integer> cache = new HashMap<>();
    private final ICacheDao cacheDao;

    public Cache(ICacheDao cacheDao) {
        this.cacheDao = cacheDao;
    }

    @Override
    public void put(Integer key, Integer value, boolean persistent) {
        cache.put(key, value);
        if (persistent) cacheDao.save(key, value); // Сохраняем в базу, если требуется
    }

    @Override
    public Integer get(Integer key) {
        return cache.get(key);
    }

    @Override
    public void load() {
        cache = cacheDao.load();
    }
}
