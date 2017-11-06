package dao;

public interface ICacheDao {
    void save(int n, int value);
    int[] load();
}
