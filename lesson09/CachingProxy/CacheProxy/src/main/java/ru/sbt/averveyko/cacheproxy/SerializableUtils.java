package ru.sbt.averveyko.cacheproxy;

import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class SerializableUtils {
    public static void serialize(final Object obj, final String fileName, final boolean compressed) {

        if (compressed)
            serializeCompressed(obj,fileName + ".compressed");
        else
            serializeNotCompressed(obj, fileName);
    }

    public static Object deserialize(final String fileName, final boolean compressed) {
        if (compressed)
            return deserializeCompressed(fileName + ".compressed");
        return deserializeNotCompressed(fileName);
    }

    private static void serializeCompressed(final Object object, final String fileName) {
        File file = new File(fileName);
        try(ObjectOutputStream output = new ObjectOutputStream(new DeflaterOutputStream(new FileOutputStream(file)))) {
            output.writeObject(object);
        } catch (IOException e) {
            System.out.println("Неудалось загрузить кэш " + e.getMessage());
        }
    }

    private static void serializeNotCompressed(final Object object, final String fileName) {
        File file = new File(fileName);
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file))) {
            output.writeObject(object);
        } catch (IOException e) {
            System.out.println("Неудалось загрузить кэш " + e.getMessage());
        }
    }

    private static Object deserializeCompressed(final String fileName) {
        Object result = null;
        File file = new File(fileName);

        try(ObjectInputStream input = new ObjectInputStream(new InflaterInputStream(new FileInputStream(file)))) {

            result = input.readObject();

        } catch (IOException e) {
            System.out.println("Неудалось загрузить кэш " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден " + e.getMessage());
        }
        return result;
    }

    private static Object deserializeNotCompressed(final String fileName) {
        Object result = null;
        File file = new File(fileName);

        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {

            result = input.readObject();

        } catch (IOException e) {
            System.out.println("Неудалось загрузить кэш " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден " + e.getMessage());
        }
        return result;
    }
}

