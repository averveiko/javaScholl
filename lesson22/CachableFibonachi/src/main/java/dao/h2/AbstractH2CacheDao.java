package dao.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractH2CacheDao {
    private static final String H2_DRIVER = "org.h2.Driver";
    private static final String H2_URL = "jdbc:h2:file:~/cache.db";

    protected Connection getConnection() {
        try {
            Class.forName(H2_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't load H2 driver", e);
        }

        try {
            return DriverManager.getConnection(H2_URL);
        } catch (SQLException e) {
            throw new RuntimeException("Can't get connection", e);
        }
    }
}
