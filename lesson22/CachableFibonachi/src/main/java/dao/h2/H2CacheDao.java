package dao.h2;

import dao.ICacheDao;
import model.Cache;

import java.sql.*;

public class H2CacheDao extends AbstractH2CacheDao implements ICacheDao {
    private final String connectionUrl;

    private static final String SQL_INSERT =
            "INSERT INTO Cache (N, Value)\n" +
                    "VALUES (?, ?);";

    private static final String SQL_UPDATE =
            "UPDATE Cache\n" +
                    "SET Value=?\n" +
                    "WHERE N=?;";

    private static final String SQL_SELECT =
            "SELECT Value FROM Cache";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS Cache(\n" +
                    "  N INT PRIMARY KEY,\n" +
                    "  Value INT NOT NULL\n" +
                    ");";

    private static final String SQL_CHECK_TABLE_SIZE =
            "SELECT COUNT(Value) AS cnt FROM Cache;";

    private static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS Cache;";

    public H2CacheDao(String connectionUrl) {
        this.connectionUrl = connectionUrl;

        //Проверяем, размер таблицы с кэшем в базе
        if ( !checkTableSize()) {
            dropTable();
            createTable();
            prepareTable();
        }
    }

    /**
     * Проверяет размер таблицы с кэшем в базе,
     * @return true если размер совпадает с размером кэша, false в противном случае
     */
    private boolean checkTableSize() {
        try (Connection connection = getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQL_CHECK_TABLE_SIZE);
            resultSet.next();

            int count = resultSet.getInt("cnt");

            if (count == Cache.MAX_N) return true;

        } catch (SQLException e) {
            return false;
        }

        return false;
    }

    /**
     * Сохраняет в базу вычисленное значение
     * @param n порядковый номер числа
     * @param value вычисленное значение
     */
    @Override
    public void save(int n, int value) {

        try (Connection connection = getConnection(connectionUrl);
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {

            statement.setInt(1, value);
            statement.setInt(2, n);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Can't save cache", e);
        }
    }

    /**
     * Загружает из базы весь кэш
     * @return массив с загруженным кэшем
     */
    @Override
    public int[] load() {
        int[] cache = new int[Cache.MAX_N + 1];

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

    /**
     * Удаляет таблицу с кэшем
     */
    private void dropTable() {
        try (Connection connection = getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            statement.execute(SQL_DROP_TABLE);

        } catch (SQLException e) {
            throw new RuntimeException("Can't drop table", e);
        }
    }

    /**
     * Создает таблицу для кэша
     */
    private void createTable() {
        try (Connection connection = getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            statement.execute(SQL_CREATE_TABLE);

        } catch (SQLException e) {
            throw new RuntimeException("Can't create table", e);
        }
    }

    /**
     * Подготавливает таблицу к сохранению кэша
     * (заполняет нулевыми значениями)
     */
    private void prepareTable() {
        try (Connection connection = getConnection(connectionUrl);
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {

            for (int i = 1; i <= Cache.MAX_N; i++) {
                statement.setInt(1, i);
                statement.setInt(2, 0);
                statement.addBatch();
            }

            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException("Can't prepare table", e);
        }
    }
}
