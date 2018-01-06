package ru.sbt.averveyko;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sbt.averveyko.ui.console.ConsoleUI;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        ConsoleUI consoleUI = context.getBean("consoleUI", ConsoleUI.class);
        consoleUI.start();
    }
}
