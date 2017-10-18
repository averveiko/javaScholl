### Зарефакторить

* Задание 1

**До рефакторинга**

```Java
package ru.sbt.bit.ood.solid.homework;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SalaryHtmlReportNotifier {

    private Connection connection;

    public SalaryHtmlReportNotifier(Connection databaseConnection) {
        this.connection = databaseConnection;
    }

    public void generateAndSendHtmlSalaryReport(String departmentId, LocalDate dateFrom, LocalDate dateTo, String recipients) {
        try {
            // prepare statement with sql
            PreparedStatement ps = connection.prepareStatement("select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary from employee emp left join" +
                    "salary_payments sp on emp.id = sp.employee_id where emp.department_id = ? and" +
                    " sp.date >= ? and sp.date <= ? group by emp.id, emp.name");
            // inject parameters to sql
            ps.setString(0, departmentId);
            ps.setDate(1, new java.sql.Date(dateFrom.toEpochDay()));
            ps.setDate(2, new java.sql.Date(dateTo.toEpochDay()));
            // execute query and get the results
            ResultSet results = ps.executeQuery();
            // create a StringBuilder holding a resulting html
            StringBuilder resultingHtml = new StringBuilder();
            resultingHtml.append("<html><body><table><tr><td>Employee</td><td>Salary</td></tr>");
            double totals = 0;
            while (results.next()) {
                // process each row of query results
                resultingHtml.append("<tr>"); // add row start tag
                resultingHtml.append("<td>").append(results.getString("emp_name")).append("</td>"); // appending employee name
                resultingHtml.append("<td>").append(results.getDouble("salary")).append("</td>"); // appending employee salary for period
                resultingHtml.append("</tr>"); // add row end tag
                totals += results.getDouble("salary"); // add salary to totals
            }
            resultingHtml.append("<tr><td>Total</td><td>").append(totals).append("</td></tr>");
            resultingHtml.append("</table></body></html>");
            // now when the report is built we need to send it to the recipients list
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            // we're going to use google mail to send this message
            mailSender.setHost("mail.google.com");
            // construct the message
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipients);
            // setting message text, last parameter 'true' says that it is HTML format
            helper.setText(resultingHtml.toString(), true);
            helper.setSubject("Monthly department salary report");
            // send the message
            mailSender.send(message);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
```

**После рефакторинга**
* Код разделен на два класса:
  * SalaryHtmlReport - генерирует html отчет, код разбит на методы:
    * getSalaryFromDB - получение данных из базы;
    * generateHTML - генерация HTML кода;
  * ReportNotifier - отправляет отчет на email.
* SQL запрос вынесен в виде константы для удобной правки;
* HTML разметка вынесена в константы для удобной правки и наглядного представления (добавлены отступы).

**Пример использования отчета:**
```Java
LocalDate dateFrom = LocalDate.of(2017,9,1);
LocalDate dateTo = LocalDate.now();
String htmlReport = new SalaryHtmlReport(connection).generate("IT", dateFrom, dateTo);
ReportNotifier.sendToMail("averveiko@gmail.com", htmlReport);
```

**Генерируемый html-код отчета:** 
```HTML
<html>
  <body>
    <table>
      <tr>
        <td>Employee</td><td>Salary</td>
      </tr>
      <tr>
      <tr>
        <td>Ivanov</td><td>100</td>
      </tr>
      <tr>
        <td>Petrov</td><td>200</td>
      </tr>
      <tr>
        <td>Sidorov</td><td>300</td>
      </tr>
      <tr>
        <td>Total</td><td>600</td>
      </tr>
    </table>
  </body>
</html>
```

**Код:**
```Java
package ru.sbt.bit.ood.solid.homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Отчет по заработной плате отдела за период
 */
public class SalaryHtmlReport {
    private final Connection connection;

    private final static String SQL_STATEMENT = "SELECT emp.id AS emp_id, emp.name AS amp_name, SUM(salary) AS salary" +
            "FROM employee emp" +
            "LEFT JOIN salary_payments sp ON emp.id = sp.employee_id" +
            "WHERE emp.department_id = ? AND sp.date >= ? AND sp.date <= ?" +
            "GROUP BY emp.id, emp.name";

    private final static String HTML_HEADER =
            "<html>\n" +
            "  <body>\n" +
            "    <table>\n" +
            "      <tr>\n" +
            "        <td>Employee</td><td>Salary</td>\n" +
            "      </tr>\n";

    private final static String HTML_BODY =
            "      <tr>\n" +
            "        <td>%s</td><td>%f</td>\n" +
            "      </tr>\n";

    private final static String HTML_TOTAL =
            "      <tr>\n" +
            "        <td>Total</td><td>%f</td>\n" +
            "      </tr>\n";

    private final static String HTML_FOOTER =
            "    </table>\n" +
            "  </body>\n" +
            "</html>\n";

    public SalaryHtmlReport(final Connection databaseConnection) {
        this.connection = databaseConnection;
    }

    /**
     * Получить данные о зарплате из БД
     * @param departmentId id отдела
     * @param dateFrom начало периода
     * @param dateTo окончание периода
     * @return ResultSet c данными
     */
    private ResultSet getSalaryFromDB(final String departmentId, final LocalDate dateFrom, final LocalDate dateTo) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_STATEMENT);
            ps.setString(0, departmentId);
            ps.setDate(1, new java.sql.Date(dateFrom.toEpochDay()));
            ps.setDate(2, new java.sql.Date(dateTo.toEpochDay()));

            return ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Сформировать HTML-отчет
     * @param salarySet ResultSet c данными из базы
     * @return HTML-отчет
     */
    private String generateHTML(final ResultSet salarySet) {
        double totals = 0;
        StringBuilder html = new StringBuilder();

        html.append(HTML_HEADER);
        try {
            while (salarySet.next()) {
                html.append(String.format(HTML_BODY, salarySet.getString("emp_name"), salarySet.getDouble("salary")));
                totals += salarySet.getDouble("salary");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        html.append(String.format(HTML_TOTAL, totals));
        html.append(HTML_FOOTER);

        return html.toString();
    }

    /**
     * Получить отчет
     * @param departmentId id отдела
     * @param dateFrom начало периода
     * @param dateTo окончание периода
     * @return HTML отчет
     */
    public String generate(String departmentId, LocalDate dateFrom, LocalDate dateTo) {
        ResultSet salarySet = getSalaryFromDB(departmentId, dateFrom, dateTo);
        if (salarySet != null)
            return generateHTML(salarySet);

        return null;
    }
}
```

```Java
package ru.sbt.bit.ood.solid.homework;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class ReportNotifier {
    /**
     * Отправляет HTML-отчет по электронной почте
     *
     * @param recipients Получатели
     * @param htmlReport Отчет
     */
    public static void sendToMail(final String recipients, final String htmlReport) {
        try {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

            mailSender.setHost("mail.google.com");
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipients);
            // setting message text, last parameter 'true' says that it is HTML format
            helper.setText(htmlReport, true);
            helper.setSubject("Monthly department salary report");
            mailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
```