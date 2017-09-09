package ru.sbt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Загрузчик плагинов из файловой системы
 */
public class PluginLoader extends ClassLoader {
    private final String pluginRootDirectory;

    public PluginLoader(ClassLoader parent, String pluginRootDirectory) {
        super(parent);
        this.pluginRootDirectory = pluginRootDirectory;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            //Получаем байт-код из файла и загружаем его
            byte b[] = fecthClassFromFS(pluginRootDirectory
                    + File.separator + name
                    + File.separator + name + ".class");
            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            return super.findClass(name);
        }
    }

    /**
     * Читает файл в массив байт
     * @param path путь к файлу
     * @return массив байт представляющий файл
     * @throws IOException ошибки при чтении файла
     */
    private byte[] fecthClassFromFS(String path) throws IOException {
        System.out.println("Загружаем плагин из файла " + path);
        Path filePath = Paths.get(path);
        return Files.readAllBytes(filePath);
    }
}
