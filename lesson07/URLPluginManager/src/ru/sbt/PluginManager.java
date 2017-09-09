package ru.sbt;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginManager {
    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        String fullPath = pluginRootDirectory + "/" + pluginName + "/";

        System.out.println("Загружаем плагин " + pluginClassName + " из " + fullPath);

        URL[] classLoaderUrls = new URL[]{new URL(fullPath)};
        URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);
        Class<?> pluginClazz = urlClassLoader.loadClass(pluginClassName);

        return (Plugin) pluginClazz.newInstance();
    }
}
