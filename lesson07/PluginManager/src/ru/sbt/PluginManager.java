package ru.sbt;

public class PluginManager {
    private final String pluginRootDirectory;
    private final PluginLoader pluginLoader;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
        this.pluginLoader = new PluginLoader(ClassLoader.getSystemClassLoader(), pluginRootDirectory);
    }

    public Plugin load(String pluginName) {
        try {
            Class clazz = pluginLoader.loadClass(pluginName);

            Plugin plugin = (Plugin) clazz.newInstance();

            plugin.doUsefull();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
