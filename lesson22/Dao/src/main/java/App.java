import Dao.StudentDataSource;
import Dao.StudentsDao;
import model.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            StudentDataSource.init();

            System.out.println("\nDrop tables if exist");
            StudentDataSource.dropTablesForTesting();

            System.out.println("\nCreate tables");
            StudentDataSource.createTablesIfNotExist();

            Student studentBill = new Student(0, "Bill", "Gates");
            Student studentSteve = new Student(0, "Steve", "Jobs");
            Student studentLinus = new Student(0, "Linus", "Torvalds");

            Connection connection = StudentDataSource.getConnection();
            StudentsDao studentsDao = new StudentsDao(connection);

            System.out.println("\nAdding students");
            studentsDao.insert(studentBill);
            studentsDao.insert(studentSteve);
            studentsDao.insert(studentLinus);

            System.out.println(studentBill);
            System.out.println(studentSteve);
            System.out.println(studentLinus);

            System.out.println("\nGetting student from DB by PK");
            System.out.println(studentsDao.getByPK(studentBill.getId()));

            System.out.println("\nGetting all students from DB");
            studentsDao.getAll().forEach(System.out::println);

            System.out.println("\nUpdate student Linus");
            studentLinus.setFirstName("LINUS");
            studentLinus.setLastName("TORVALDS");
            studentsDao.update(studentLinus);
            System.out.println(studentsDao.getByPK(studentLinus.getId()));

            System.out.println("\nDelete student Bill");
            studentsDao.delete(studentBill);
            studentsDao.getAll().forEach(System.out::println);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
