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