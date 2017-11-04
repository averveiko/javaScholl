package dao.h2;

import dao.ICacheDao;

import java.sql.Connection;
import java.sql.SQLException;

public class H2CacheDao extends AbstractH2CacheDao implements ICacheDao {
    @Override
    public void save(int[] cache) {
        try(Connection connection = getConnection()) {

        } catch (SQLException e) {
            throw new RuntimeException("Can't save cache", e);
        }
    }

    @Override
    public int[] load() {
        try(Connection connection = getConnection()) {

        } catch (SQLException e) {
            throw new RuntimeException("Can't save cache", e);
        }
        return new int[0];
    }
}
