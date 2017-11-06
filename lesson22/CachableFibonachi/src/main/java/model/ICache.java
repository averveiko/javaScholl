package model;

public interface ICache {
    void put(Integer key, Integer value, boolean persistent);
    Integer get(Integer key);
    void load();
}
