package ru.sbt.averveyko.beans;

public class ConsoleEventLogger implements EventLogger{
    @Override
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
