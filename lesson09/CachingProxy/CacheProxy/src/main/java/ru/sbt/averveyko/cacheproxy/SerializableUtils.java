package ru.sbt.averveyko.cacheproxy;

import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class SerializableUtils {
    private static final String SERIALIZE_EXCEPTION_MSG = "Неудалось записать кэш в файл ";
    private static final String DESERIALIZE_EXCEPTION_MSG = "Неудалось загрузить кэш из файла ";
    private static final String CLASSNOTFOUND_EXCEPTION_MSG = "Класс не найден ";

    public static void serialize(final Object obj, final String fileName, final boolean compressed) {
        File file = new File(fileName);
        ObjectOutputStream objectOutputStream = null;
        try {
            OutputStream outputStream = new FileOutputStream(file);
            if (compressed) outputStream = new DeflaterOutputStream(outputStream);
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            System.out.println(SERIALIZE_EXCEPTION_MSG + e.getMessage());
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException ignore) {
                }
            }
        }
    }

    public static Object deserialize(final String fileName, final boolean compressed) {
        Object obj = null;
        File file = new File(fileName);
        ObjectInputStream objectInputStream = null;
        try {
            InputStream inputStream = new FileInputStream(file);
            if (compressed) inputStream = new InflaterInputStream(inputStream);
            objectInputStream = new ObjectInputStream(inputStream);
            obj = objectInputStream.readObject();
        } catch (IOException e) {
            System.out.println(DESERIALIZE_EXCEPTION_MSG + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(CLASSNOTFOUND_EXCEPTION_MSG + e.getMessage());
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException ignore) {
                }
            }
        }
        return obj;
    }
}

