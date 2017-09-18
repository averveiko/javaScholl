package ru.sbt.averveyko.cacheproxy;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.junit.Assert.*;

public class SerializableUtilsTest {

    private static final String fileName = "temp.bin";

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void deserialize() throws Exception {

        String testObj = "test object";
        File createdFile = folder.newFile(fileName);
        SerializableUtils.serialize(testObj,createdFile.getAbsolutePath(), true);

        String result = (String)SerializableUtils.deserialize(createdFile.getAbsolutePath(), true);
        assertEquals(testObj, result);
    }
}
