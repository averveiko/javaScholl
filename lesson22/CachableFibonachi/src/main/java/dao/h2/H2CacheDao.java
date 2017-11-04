package dao.h2;

import dao.ICacheDao;

import java.sql.*;

public class H2CacheDao extends AbstractH2CacheDao implements ICacheDao {
    // Максимальное n = 46, т.к. 47-й элемент последовательности фибоначчи = 2 971 215 073
    // что больше Integer.MAX_VALUE (по условиям задания оперируем int)
    private static final int MAX_N = 46;
    private final String connectionUrl;

    private static final String SQL_INSERT =
            "INSERT INTO Cache (N, Value)\n" +
                    "VALUES (?, ?);";

    private static final String SQL_SELECT =
            "SELECT Value FROM Cache";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS Cache(\n" +
                    "  N INT PRIMARY KEY,\n" +
                    "  Value INT NOT NULL\n" +
                    ");";

    private static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS Cache;";

    public H2CacheDao(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    @Override
    public void save(int[] cache) {
        dropTable();  // Drop old cache if exists
        createTable();// Create table for new cache

        try (Connection connection = getConnection(connectionUrl);
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {

            for (int i = 1; i < cache.length; i++) {
                statement.setInt(1, i);
                statement.setInt(2, cache[i]);
                statement.addBatch();
            }

            statement.executeBatch();

        } catch (SQLException e) {
            throw new RuntimeException("Can't save cache", e);
        }
    }

    @Override
    public int[] load() {
        int[] cache = new int[MAX_N + 1];

        try (Connection connection = getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            int n = 0;

            while (resultSet.next()) {
                cache[++n] = resultSet.getInt("Value");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Can't load cache", e);
        }
        return cache;
    }

    private void createTable() {
        try (Connection connection = getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            statement.execute(SQL_CREATE_TABLE);

        } catch (SQLException e) {
            throw new RuntimeException("Can't create table", e);
        }
    }

    private void dropTable() {
        try (Connection connection = getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            statement.execute(SQL_DROP_TABLE);

        } catch (SQLException e) {
            throw new RuntimeException("Can't drop table", e);
        }
    }
}
