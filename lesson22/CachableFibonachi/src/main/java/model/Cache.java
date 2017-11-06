package model;

import dao.ICacheDao;

/**
 * Кэш вычисленных чисел фибоначчи
 * Максимальное n = 46, т.к. 47-й элемент последовательности фибоначчи = 2 971 215 073,
 * что больше Integer.MAX_VALUE (по условиям задания оперируем int)
 */

public class Cache implements ICache {
    public static final int MAX_N = 46;
    private int[] cache = new int[MAX_N+1];
    private final ICacheDao cacheDao;

    public Cache(ICacheDao cacheDao) {
        this.cacheDao = cacheDao;
    }

    @Override
    public void put(int n, int value, boolean persistent) {
        if (n < 1 || n > MAX_N)
            throw new IllegalArgumentException("n должен быть > 0 и <= " + MAX_N);

        cache[n] = value; // Сохраняем в память
        if (persistent) cacheDao.save(n, value); // Сохраняем в базу, если требуется
    }

    @Override
    public int get(int n) {
        if (n < 1 || n > MAX_N)
            throw new IllegalArgumentException("n должен быть > 0 и <= " + MAX_N);

        return cache[n];
    }

    @Override
    public void load() {
        cache = cacheDao.load();
    }
}
