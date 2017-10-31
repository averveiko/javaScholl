package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDataSource {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:file:~/student_h2.db";
    private static final String SQL_CREATE_TABLES =
            "CREATE TABLE IF NOT EXISTS Students(\n" +
            "  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
            "  first_name VARCHAR(50) NOT NULL,\n" +
            "  last_name VARCHAR(50) NOT NULL\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE IF NOT EXISTS Lessons(\n" +
            "  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
            "  title VARCHAR(100) NOT NULL,\n" +
            "  date TIMESTAMP\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE IF NOT EXISTS Student_visits(\n" +
            "  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
            "  student_id INT,\n" +
            "  lesson_id INT,\n" +
            "  CONSTRAINT FK_StudentsStudentsVisit FOREIGN KEY (student_id) REFERENCES Students(id),\n" +
            "  CONSTRAINT FK_LessonsStudentsVisit FOREIGN KEY (lesson_id) REFERENCES Lessons(id)\n" +
            ");";

    public static void init() throws ClassNotFoundException {
        Class.forName(DB_DRIVER);
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void createSchemaIfNotExist() throws SQLException {
        try(Connection connection = getConnection();
        Statement statement = connection.createStatement()){
            statement.execute(SQL_CREATE_TABLES);
        }

    }

}
