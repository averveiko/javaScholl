package ru.sbt.averveyko.beans;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Event {
    private static int id;
    private String msg;
    private Date date;
    private DateFormat df;

    private static final AtomicInteger AUTO_ID = new AtomicInteger(100);

    public static boolean isDay() {
        LocalTime time = LocalTime.now();
        return time.getHour() > 8 && time.getHour() < 17;
    }

    public Event(Date date, DateFormat df) {
        this.id = AUTO_ID.getAndIncrement();
        this.date = date;
        this.df = df;
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                "}\r\n";
    }
}
