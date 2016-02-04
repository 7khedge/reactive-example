package com.sf.chronicle.queue;

import com.google.common.base.Preconditions;

import java.io.File;

/**
 * Created by adityasofat on 21/01/2016.
 */
public class PersistentArrayBuilder {
    private String directory;
    private String name;
    private int fileSize;
    private int itemSize;

    public static PersistentArrayBuilder builder() {
        return new PersistentArrayBuilder();
    }

    public PersistentArrayBuilder persistentRootDirectory(String persistentRootDirectory) {
        Preconditions.checkNotNull(persistentRootDirectory);
        Preconditions.checkArgument(!persistentRootDirectory.isEmpty(),"directory ["+ persistentRootDirectory + "] cannot be blank");
        Preconditions.checkArgument(new File(persistentRootDirectory).exists(),"directory [" + persistentRootDirectory + "] does not exist");
        this.directory = persistentRootDirectory;
        return this;
    }

    public PersistentArrayBuilder persistentArrayName(String persistentArrayName) {
        Preconditions.checkNotNull(persistentArrayName,"name cannot be null");
        Preconditions.checkArgument(!persistentArrayName.isEmpty(),"name cannot be blank");
        this.name = persistentArrayName;
        return this;
    }

    public PersistentArrayBuilder fileSize(int fileSize) {
        Preconditions.checkArgument(fileSize > 0, "FileSize must be greater than 0");
        this.fileSize  = fileSize;
        return this;
    }

    public PersistentArrayBuilder itemSize(int itemSize) {
        Preconditions.checkArgument(itemSize > 0, "ItemSize must be greater than 0");
        this.itemSize  =  itemSize;
        return this;
    }

    public PersistentArray build() {
        PersistentArray persistentArray = new PersistentArray(this.directory + File.separator + this.name, fileSize, itemSize);
        persistentArray.init();
        return persistentArray;
    }
}
