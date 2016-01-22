package com.sf.chronicle.queue;

import java.io.File;

/**
 * Created by adityasofat on 21/01/2016.
 */
public class PersistenceSpace {
    private final File directory;
    private final String name;
    private String path;

    PersistenceSpace(String baseDirectory, String name) {
        this.name = name;
        this.path = baseDirectory + getFileSeparator(baseDirectory) + name;
        this.directory = new File(path);
    }

    public void createPersistenceSpace() {
        if (directory.exists()) {
            removePersistenceSpace();
        }
        if (!directory.mkdir()) {
            throw new RuntimeException("Could not create directory [" + directory.getAbsolutePath() + "]");
        }
    }

    public void removePersistenceSpace() {
        if (directory.exists()) {
            String[] entries = directory.list();
            for (String fileName : entries) {
                File currentFile = new File(directory.getPath(), fileName);
                if(!currentFile.delete()){
                    throw new RuntimeException("Could not delete file [" + currentFile.getAbsolutePath() + "]");
                }
            }
            if(!directory.delete()){
                throw new RuntimeException("Could not directory file [" + directory.getAbsolutePath() + "]");
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    private String getFileSeparator(String baseDirectory) {
        if (baseDirectory.endsWith("\\") || baseDirectory.endsWith("/"))
            return "";
        return File.separator;
    }
}