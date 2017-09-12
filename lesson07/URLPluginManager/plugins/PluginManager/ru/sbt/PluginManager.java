package ru.sbt;

import ru.sbt.Plugin;

public class PluginManager implements Plugin {
    @Override
    public void doUsefull() {
        System.out.println("Plugin " + this.getClass() + " успешно запущен ...");
    }
}
