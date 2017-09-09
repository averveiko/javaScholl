package ru.sbt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Загрузчик плагинов из файловой системы
 */
public class CryptoPluginLoader extends ClassLoader {
    private final String pluginRootDirectory;
    private final String key;

    public CryptoPluginLoader(ClassLoader parent, String pluginRootDirectory, String key) {
        super(parent);
        this.pluginRootDirectory = pluginRootDirectory;
        this.key = key;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            //Получаем байт-код из файла и загружаем его
            byte b[] = fecthClassFromFS(pluginRootDirectory
                    + File.separator + name + ".class");

            //"Расшифровываем" байт-код
            b = decryptByte(b, this.key.getBytes());

            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            return super.findClass(name);
        }
    }

    /**
     * Читает файл в массив байт
     *
     * @param path путь к файлу
     * @return массив байт представляющий файл
     * @throws IOException ошибки при чтении файла
     */
    private byte[] fecthClassFromFS(String path) throws IOException {
        System.out.println("Загружаем плагин из файла " + path);
        Path filePath = Paths.get(path);
        return Files.readAllBytes(filePath);
    }

    private byte[] decryptByte(byte[] content, byte[] key) {
        byte[] result = new byte[content.length];
        for (int i = 0; i < content.length; i++) {
            result[i] = (byte) (content[i] ^ key[i % key.length]);
        }
        return result;
    }
}