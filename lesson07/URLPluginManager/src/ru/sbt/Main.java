package ru.sbt;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            String current = new File( "." ).toURI().normalize().toURL().toString();

            PluginManager pluginManager = new PluginManager(current + "plugins");

            Plugin printer = pluginManager.load("Printer","PrinterPlugin");
            printer.doUsefull();

            Plugin anotherPlugin = pluginManager.load("Another","AnotherPlugin");
            anotherPlugin.doUsefull();

            Plugin printerAverveyko = pluginManager.load("Printer","ru.sbt.averveyko.PrinterPlugin");
            printerAverveyko.doUsefull();

            Plugin printerIvanov = pluginManager.load("Printer","ru.sbt.ivanov.PrinterPlugin");
            printerIvanov.doUsefull();

            Plugin pluginManagerPlugin = pluginManager.load("PluginManager","ru.sbt.PluginManager");
            pluginManagerPlugin.doUsefull();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
