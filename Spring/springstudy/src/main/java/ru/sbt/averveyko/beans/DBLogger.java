package ru.sbt.averveyko.beans;

import org.springframework.jdbc.core.JdbcTemplate;

public class DBLogger implements EventLogger {
    private JdbcTemplate jdbcTemplate;

    public DBLogger(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void logEvent(Event event) {
        jdbcTemplate.update(
                "INSERT INTO t_event (id, msg) VALUES (?,?)",
                event.getId(),
                event.toString()
        );
        System.out.println("Events count: " + getEventsCount());
    }

    public void createDB() {
        jdbcTemplate.update("CREATE TABLE t_event (" + "id INT NOT NULL PRIMARY KEY," + "date TIMESTAMP,"
                + "msg VARCHAR(255)" + ")");

        System.out.println("Created table t_event");
    }

    public int getEventsCount(){
        int count = jdbcTemplate.queryForObject(
          "SELECT count(*) FROM t_event",
                Integer.class);

        return count;
    }

    public String getMsgFromEvent(int eventID) {
        // Получение ряда или нескольких рядов см тут https://www.youtube.com/watch?v=2fAhdDwjRSA
        String msg = jdbcTemplate.queryForObject(
                "select msg from t_event where id = ?",
                new Object[]{1}, String.class
        );
        return msg;
    }
}
