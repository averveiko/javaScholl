package ru.sbt;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

public class PluginLoader extends URLClassLoader {
    public PluginLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public PluginLoader(URL[] urls) {
        super(urls);
    }

    public PluginLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        //Если это плагин - грузим сразу
        if (name.startsWith("ru.plugin")) return this.findClass(name);
        //Иначе классически делегируем загрузку родителю
        return super.loadClass(name);
    }
}
