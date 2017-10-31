package Dao;

import model.Student;

import java.sql.*;

public class StudentsDao {
    private static final String SQL_INSERT =
            "INSERT INTO Students(first_name, last_name)\n" +
            "VALUES (?, ?);";

    private Connection connection;

    public StudentsDao(Connection connection) {
        this.connection = connection;
    }

    public void insert(Student student){
        try(PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("Creating student failed, no rows affected.");
            }

            try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                if (generatedKeys.next()) {
                    student.setId(generatedKeys.getInt(1));
                } else {
                    throw new RuntimeException("Creating student failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Creating student failed, sql exception: " + e.getMessage());
        }
    }
}
