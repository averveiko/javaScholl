import Dao.StudentDataSource;
import Dao.StudentsDao;
import model.Student;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        System.out.println("Starting");
        try {
            StudentDataSource.init();
            StudentDataSource.createSchemaIfNotExist();

            Student studentBill = new Student(0, "Bill", "Gates");
            Student studentSteve = new Student(0, "Steve", "Jobs");
            Student studentLinus = new Student(0, "Linus", "Torvalds");

            Connection connection = StudentDataSource.getConnection();
            StudentsDao studentsDao = new StudentsDao(connection);

            studentsDao.insert(studentBill);
            studentsDao.insert(studentSteve);
            studentsDao.insert(studentLinus);

            System.out.println(studentBill);
            System.out.println(studentSteve);
            System.out.println(studentLinus);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
