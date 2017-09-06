package com.sbt.javaschool.averveyko.ProxyCalculator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CacheFileStorage {
    private final String fileName;

    public CacheFileStorage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Сохраняет кэш в файл
     *
     * @param map Сохраняемый map
     */
    public void save(Map<String, Double> map) {
        String mapCache = "";
        //Генерируем строку для записи в файл
        for (String s : map.keySet()) {
            mapCache += s + ";" + map.get(s) + "\n";
        }

        Path filePath = Paths.get(this.fileName);

        try {
            Files.write(filePath, mapCache.getBytes());
        } catch (IOException e) {
            System.out.println("Невозможно сохранить файл " + e.getMessage());
        }

        System.out.println("Кеш успешно сохранен в файл " + filePath.toAbsolutePath());
    }

    /**
     * Загружает Кэш из файла
     *
     * @return Map - кэш значений "выражение, результат"
     */
    public Map<String, Double> load() {

        Path filePath = Paths.get(this.fileName);

        Map<String, Double> map = new HashMap<>();

        //Проверяем существует ли файл
        if (!Files.exists(filePath)) {
            System.out.println("Файл " + this.fileName + " не найден. Создан новый кэш.");
            //Возвращаем пустой мар
            return map;
        }
        //Читаем файл в строку
        String mapCache = "";
        try {
            mapCache = new String(Files.readAllBytes(filePath));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл " + e.getMessage() + ". Создан новый кэш.");
            //Возвращаем пустой мар
            return map;
        }


        //Парсим считанный кэш в мапу
        for (String line : mapCache.split("\n")) {
            String[] keyValue = line.split(";");
            map.put(keyValue[0], Double.parseDouble(keyValue[1]));
        }

        System.out.println("Кеш успешно загружен из файла " + filePath.toAbsolutePath());
        return map;
    }
}
