package com.sbt.javaschool.averveyko.ProxyCalculator;

import java.io.*;
import java.nio.file.Files;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheFileStorage {
    private final String fileName;

    public CacheFileStorage(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Сохраняет кэш в файл
     *
     * @param map Сохраняемый map
     */
    public void save(final Map<String, Double> map) {
        File file = new File(fileName);
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file))) {
            output.writeObject(map);
        } catch (IOException e) {
            System.out.println("Неудалось сохранить кэш " + e.getMessage());
        }
        System.out.println("Кеш успешно сохранен в файл " + fileName);
    }

    /**
     * Загружает Кэш из файла
     *
     * @return Map - кэш значений "выражение, результат"
     */
    public Map<String, Double> load() {
        Map<String, Double> map = new ConcurrentHashMap<>();
        File file = new File(fileName);

        if (!Files.exists(file.toPath())) {
            System.out.println("Файл " + fileName + " не найден. Создан новый кэш.");
            //Возвращаем пустой мар
            return map;
        }

        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
            map = (Map<String, Double>)input.readObject();
        } catch (IOException e) {
            System.out.println("Неудалось загрузить кэш " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден " + e.getMessage());
        }

        System.out.println("Кеш успешно загружен из файла " + fileName);
        return map;
    }
}
