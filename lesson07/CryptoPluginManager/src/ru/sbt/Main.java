package ru.sbt;

public class Main {

    public static void main(String[] args) {
        CryptoPluginManager cryptoPluginManager = new CryptoPluginManager("plugins", "secretkey");

        try {

            cryptoPluginManager.load("PrinterPlugin").doUsefull();
            cryptoPluginManager.load("AnotherPlugin").doUsefull();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
