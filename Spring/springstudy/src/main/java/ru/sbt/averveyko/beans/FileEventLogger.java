package ru.sbt.averveyko.beans;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileEventLogger implements EventLogger{
    private String fileName;
    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    protected void init() throws IOException {
        this.file = new File(fileName);
        if (! file.canWrite()) throw new IOException("can't access to " + fileName);  //Check for write
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString(), StandardCharsets.UTF_8,true);
        } catch (IOException e) {
            System.err.println("Error while write event log");
        }
    }
}
