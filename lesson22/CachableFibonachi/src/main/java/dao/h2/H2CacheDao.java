package dao.h2;

import dao.ICacheDao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class H2CacheDao extends AbstractH2CacheDao implements ICacheDao {
    private final String connectionUrl;

    /**
     * Updates existing rows, and insert rows that don't exist.
     * If no key column is specified, the primary key columns are used to find the row.
     * If more than one row per new row is affected, an exception is thrown.
     * If the table contains an auto-incremented key or identity column, and the row was updated,
     * the generated key is set to 0; otherwise it is set to the new key.
     */
    private static final String SQL_MERGE =
            "MERGE INTO CACHE KEY(Key) VALUES(?, ?);";

    private static final String SQL_SELECT =
            "SELECT Key, Value FROM Cache";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS Cache(\n" +
                    "  Key INT PRIMARY KEY,\n" +
                    "  Value INT NOT NULL\n" +
                    ");";

    public H2CacheDao(String connectionUrl) {
        this.connectionUrl = connectionUrl;

        createTable();
    }

    /**
     * Сохраняет в базу вычисленное значение
     *
     * @param key   ключ
     * @param value вычисленное значение
     */
    @Override
    public void save(Integer key, Integer value) {
        try (Connection connection = getConnection(connectionUrl);
             PreparedStatement statement = connection.prepareStatement(SQL_MERGE)) {

            statement.setInt(1, key);
            statement.setInt(2, value);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Can't save cache", e);
        }
    }

    /**
     * Загружает из базы весь кэш
     *
     * @return Map с загруженным кэшем
     */
    @Override
    public Map<Integer, Integer> load() {
        Map<Integer, Integer> cache = new HashMap<>();

        try (Connection connection = getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            while (resultSet.next()) {
                cache.put(
                        resultSet.getInt("Key"),
                        resultSet.getInt("Value")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Can't load cache", e);
        }
        return cache;
    }

    /**
     * Создает таблицу для кэша, если она отсуствует
     */
    private void createTable() {
        try (Connection connection = getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            statement.execute(SQL_CREATE_TABLE);

        } catch (SQLException e) {
            throw new RuntimeException("Can't create table", e);
        }
    }
}
