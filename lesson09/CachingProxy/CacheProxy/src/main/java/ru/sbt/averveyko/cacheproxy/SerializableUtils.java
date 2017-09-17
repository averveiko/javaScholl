package ru.sbt.averveyko.cacheproxy;


import java.io.*;

public class SerializableUtils {
    public static void serialize (Object obj, String fileName) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            System.out.println("Неудалось записать в файл " + fileName);
            e.printStackTrace();
        }
    }

    public static Object deserialize (String fileName) {
        try(FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return objectInputStream.readObject();
        } catch (IOException e) {
            System.out.println("Неудалось прочитать файл " + fileName);
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден");
        }
        return null;
    }
}

