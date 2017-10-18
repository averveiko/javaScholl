package test;

import jdk.nashorn.internal.codegen.FieldObjectCreator;

import java.time.LocalDate;

public class Main {
    private static final String htmlHeader =
            "<html>\n" +
                    "  <body>\n" +
                    "    <table>\n" +
                    "      <tr>\n" +
                    "        <td>Employee</td><td>Salary</td>\n" +
                    "      </tr>\n" +
                    "      <tr>\n";

    private static final String htmlBody =
            "      <tr>\n" +
                    "        <td>%s</td><td>%s</td>\n" +
                    "      </tr>\n";

    private static final String htmlTotal =
            "      <tr>\n" +
                    "        <td>Total</td><td>%s</td>\n" +
                    "      </tr>\n";

    private static final String htmlFooter =
            "    </table>\n" +
                    "  </body>\n" +
                    "</html>\n";

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append(htmlHeader);
        sb.append(String.format(htmlBody, "Ivanov", 100));
        sb.append(String.format(htmlBody, "Petrov", 200));
        sb.append(String.format(htmlBody, "Sidorov", 300));
        sb.append(String.format(htmlTotal, 600));
        sb.append(htmlFooter);

        System.out.println(sb);


        LocalDate startDate = LocalDate.of(2017,9,1);
        LocalDate endDate = LocalDate.now();
    }
}
