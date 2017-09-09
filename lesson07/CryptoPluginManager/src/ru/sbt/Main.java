package ru.sbt;

public class Main {

    public static void main(String[] args) {
        PluginManager pluginManager = new PluginManager("plugins");

        pluginManager.load("PrinterPlugin");
        pluginManager.load("AnotherPlugin");

    }
}
