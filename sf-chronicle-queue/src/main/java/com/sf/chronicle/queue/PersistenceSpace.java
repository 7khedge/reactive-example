package com.sf.chronicle.queue;

import java.io.File;

/**
 * Created by adityasofat on 21/01/2016.
 */
public class PersistenceSpace {
    private final File directory;
    private final String name;
    private final boolean keepDirectory;
    private String path;

    PersistenceSpace(String baseDirectory, String name) {
        this(baseDirectory, name, false);
    }

    public PersistenceSpace(String baseDirectory, String name, boolean keepDirectory) {
        this.name = name;
        this.keepDirectory = keepDirectory;
        this.path = baseDirectory + getFileSeparator(baseDirectory) + name;
        this.directory = new File(path);
    }

    public void createPersistenceSpace() {
        removePersistenceDirectory();
        createPersistenceDirectorty();
    }

    private void createPersistenceDirectorty() {
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                throw new RuntimeException("Could not create directory [" + directory.getAbsolutePath() + "]");
            }
        }
    }

    public void removePersistenceDirectory() {
        if (!this.keepDirectory) {
            if (directory.exists()) {
                removeDirectoryFiles(directory.list());
                removeDirectory();
            }
        }
    }

    private void removeDirectoryFiles(String[] fileList) {
        for (String fileName : fileList) {
            File currentFile = new File(directory.getPath(), fileName);
            if (!currentFile.delete()) {
                throw new RuntimeException("Could not delete file [" + currentFile.getAbsolutePath() + "]");
            }
        }
    }

    private void removeDirectory() {
        if (!this.directory.delete()) {
            throw new RuntimeException("Could not directory file [" + directory.getAbsolutePath() + "]");
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