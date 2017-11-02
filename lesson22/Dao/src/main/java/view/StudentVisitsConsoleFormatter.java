package view;

import model.StudentVisitsJoin;

import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

public class StudentVisitsConsoleFormatter {
    public static void printStudentVisitsEntryList(List<StudentVisitsJoin> studentVisits) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);

        System.out.println("\n\t\t\t\t\t\tStudent attendance\n");
        formatter.format("%5s|%17s|%20s|%30s\n", "N", "LESSON DATE", "LESSON TITLE", "STUDENT NAME");
        sb.append("-----|-----------------|--------------------|------------------------------\n");

        int num = 0;
        for (StudentVisitsJoin studentVisit : studentVisits) {
            formatter.format("%5d|%17s|%20s|%30s\n",
                    ++num,
                    new SimpleDateFormat("yyyy.MM.dd HH:mm").format(studentVisit.getDate()),
                    studentVisit.getTitle(),
                    studentVisit.getName());
        }

        System.out.println(sb);
    }
}
