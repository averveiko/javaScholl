package model;

public interface ICache {
    void put(int n, int value, boolean persistent);
    int get(int n);
    void load();
}
