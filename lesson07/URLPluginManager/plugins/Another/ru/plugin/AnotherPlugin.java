package ru.plugin;

import ru.sbt.Plugin;

public class AnotherPlugin implements Plugin {
    @Override
    public void doUsefull() {
        System.out.println("Plugin " + this.getClass() + " успешно запущен ...");
    }
}
