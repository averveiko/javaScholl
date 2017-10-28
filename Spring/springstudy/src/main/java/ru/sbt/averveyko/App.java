package ru.sbt.averveyko;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sbt.averveyko.beans.Client;
import ru.sbt.averveyko.beans.Event;
import ru.sbt.averveyko.beans.EventLogger;
import ru.sbt.averveyko.beans.EventType;

import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private  Map<EventType, EventLogger> loggers;

    public App() {
    }

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        super();
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        Client client = ctx.getBean(Client.class);
        System.out.println("Client says: " + client.getGreeting());

        app.logEvents(ctx);
    }

    public void logEvent(EventType type, Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }

    public void logEvents(ApplicationContext ctx) {
        Event event = ctx.getBean(Event.class);
        logEvent(EventType.INFO, event, "Some info event for 1");

        event = ctx.getBean(Event.class);
        logEvent(EventType.INFO, event, "One more info event for 1");

        event = ctx.getBean(Event.class);
        logEvent(EventType.INFO, event, "And one more info event for 1");

        event = ctx.getBean(Event.class);
        logEvent(EventType.ERROR, event, "Some error event for 2");

        event = ctx.getBean(Event.class);
        logEvent(null, event, "Some default event for 3");
    }
}
