package ru.sbt;

public class CryptoPluginManager {
    private final CryptoPluginLoader cryptoPluginLoader;

    public CryptoPluginManager(String pluginRootDirectory, String key) {
        this.cryptoPluginLoader = new CryptoPluginLoader(ClassLoader.getSystemClassLoader(), pluginRootDirectory, key);
    }

    public Plugin load(String pluginName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = cryptoPluginLoader.loadClass(pluginName);
        return (Plugin) clazz.newInstance();
    }
}
