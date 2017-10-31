import Dao.DataSourceUtil;
import Dao.LessonsDao;
import Dao.StudentsDao;
import model.Lesson;
import model.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class App {
    public static void main(String[] args) {
        try {
            DataSourceUtil.init();

            System.out.println("\nDrop tables if exist");
            DataSourceUtil.dropTablesForTesting();

            System.out.println("\nCreate tables");
            DataSourceUtil.createTablesIfNotExist();

            //Test StudentDao
            Connection connection = DataSourceUtil.getConnection();
            StudentsDao studentsDao = new StudentsDao(connection);

            Student studentBill = new Student(0, "Bill", "Gates");
            Student studentSteve = new Student(0, "Steve", "Jobs");
            Student studentLinus = new Student(0, "Linus", "Torvalds");

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

            //Test LessonDao
            LessonsDao lessonsDao = new LessonsDao(connection);

            System.out.println("\nAdding lessons");
            Lesson firstLesson = new Lesson(0,"mathematics", Timestamp.valueOf("2017-12-01 09:00:00"));
            Lesson secondLesson = new Lesson(0,"physics", Timestamp.valueOf("2017-12-01 11:00:00"));
            Lesson thirdLesson = new Lesson(0,"programming", Timestamp.valueOf("2017-12-01 13:00:00"));

            lessonsDao.insert(firstLesson);
            lessonsDao.insert(secondLesson);
            lessonsDao.insert(thirdLesson);

            System.out.println("\nGetting lesson from DB by PK");
            System.out.println(lessonsDao.getByPK(firstLesson.getId()));

            System.out.println("\nGetting all lessons from DB");
            lessonsDao.getAll().forEach(System.out::println);

            System.out.println("\nUpdate second lesson");
            secondLesson.setTitle("PHYSICS");
            secondLesson.setDate(Timestamp.valueOf("2017-12-01 11:30:00"));
            lessonsDao.update(secondLesson);
            System.out.println(lessonsDao.getByPK(secondLesson.getId()));

            System.out.println("\nDelete third lesson");
            lessonsDao.delete(thirdLesson);
            lessonsDao.getAll().forEach(System.out::println);

            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
