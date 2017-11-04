package dao;

public interface ICacheDao {
    void save(int[] cache);
    int[] load();
}
