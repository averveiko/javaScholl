package ru.sbt;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class PluginManager {
    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName) throws MalformedURLException,
            ClassNotFoundException, IllegalAccessException, InstantiationException {

        URL url[] = {new File(pluginRootDirectory + "\\" + pluginName + "\\").toURI().normalize().toURL()};
        System.out.println(url[0].toString());

        PluginLoader pluginLoader = new PluginLoader(url);
        return (Plugin) pluginLoader.loadClass(pluginClassName).newInstance();
    }
}
