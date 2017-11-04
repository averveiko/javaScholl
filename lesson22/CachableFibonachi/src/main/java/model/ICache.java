package model;

public interface ICache {
    void put(int n, int value);
    int get(int n);
    void save();
    void load();
}
