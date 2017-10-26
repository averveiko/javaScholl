package ru.sbt.averveyko;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sbt.averveyko.beans.Client;
import ru.sbt.averveyko.beans.Event;
import ru.sbt.averveyko.beans.EventLogger;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public void logEvent(Event event) {
        event.setMsg(event.getMsg().replaceAll(client.getId(), client.getFullName()));
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        Event event = (Event) ctx.getBean("event");
        event.setMsg("Some event for user 1");

        app.logEvent(event);
    }
}
