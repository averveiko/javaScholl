import dao.StudentVisitsDao;
import dao.TableUtil;
import dao.LessonsDao;
import dao.StudentsDao;
import model.Lesson;
import model.Student;
import model.StudentVisits;
import view.StudentVisitsConsoleFormatter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;

public class App {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:file:~/student_h2.db";

    public static void main(String[] args) {

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Can't load driver for h2 " + e.getMessage());
        }

        try (Connection connection = DriverManager.getConnection(DB_URL)) {

            System.out.println("\nDrop tables if exist");
            TableUtil.dropTablesForTesting(connection);

            System.out.println("\nCreate tables");
            TableUtil.createTablesIfNotExist(connection);

            //Test StudentDao
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
            Lesson firstLesson = new Lesson(0, "mathematics", Timestamp.valueOf("2017-12-01 09:00:00"));
            Lesson secondLesson = new Lesson(0, "physics", Timestamp.valueOf("2017-12-01 11:00:00"));
            Lesson thirdLesson = new Lesson(0, "programming", Timestamp.valueOf("2017-12-01 13:00:00"));

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

            //Test StudentVisitsDao
            StudentVisitsDao studentVisitsDao = new StudentVisitsDao(connection);

            System.out.println("\nAdding student visits");
            StudentVisits visit1 = new StudentVisits(0, 2,1);
            StudentVisits visit2 = new StudentVisits(0, 3,2);
            StudentVisits visit3 = new StudentVisits(0, 3,1);
            StudentVisits visit4 = new StudentVisits(0, 2,2);

            studentVisitsDao.insert(visit1);
            studentVisitsDao.insert(visit2);
            studentVisitsDao.insert(visit3);
            studentVisitsDao.insert(visit4);

            System.out.println("\nGetting student visits from DB by PK");
            System.out.println(studentVisitsDao.getByPK(visit1.getId()));

            System.out.println("\nGetting all student visits from DB");
            studentVisitsDao.getAll().forEach(System.out::println);

            System.out.println("\nUpdate student visits 3");
            visit3.setStudentId(3);
            visit3.setLessonId(1);
            studentVisitsDao.update(visit3);
            System.out.println(studentVisitsDao.getByPK(visit3.getId()));

            System.out.println("\nDelete student visits 3");
            studentVisitsDao.delete(visit3);
            studentVisitsDao.getAll().forEach(System.out::println);


            StudentVisitsConsoleFormatter.printStudentVisitsEntryList(studentVisitsDao.getAllJoin());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
