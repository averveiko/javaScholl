package dao;

import java.util.Map;

public interface ICacheDao {
    void save(Integer key, Integer value);
    Map<Integer, Integer> load();
}
