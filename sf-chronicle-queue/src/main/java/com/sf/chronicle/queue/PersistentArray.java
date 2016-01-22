package com.sf.chronicle.queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by adityasofat on 21/01/2016.
 */
public class PersistentArray {
    private final String persistentArrayPath;
    private final int fileSize;
    private final int itemSize;
    private FileChannel fileChannel;
    private MappedByteBuffer buffer;
    private RandomAccessFile randomAccessFile;

    public PersistentArray(String persistentArrayPath, int fileSize, int itemSize) {
        checkNotNull(persistentArrayPath);
        checkArgument(fileSize > 128, "fileSize should be greater than 128 bytes");
        checkArgument(itemSize > 1, "itemSize should be greater 1");
        checkArgument(fileSize > itemSize, "fileSize should be greater than itemSize");
        this.persistentArrayPath = persistentArrayPath;
        this.fileSize = fileSize;
        this.itemSize = itemSize;
    }

    public void init()  {
        try {
            this.randomAccessFile = new RandomAccessFile(new File(persistentArrayPath), "rw");
            this.fileChannel = randomAccessFile.getChannel();
            this.buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to create file at path[" + persistentArrayPath + "]",e);
        } catch (IOException e) {
            throw new RuntimeException("Unable to create file at path[" + persistentArrayPath + "]",e);
        }

    }

    public void close()  {
        try {
            this.fileChannel.close();
        } catch (IOException e) {
            throw new RuntimeException("Unable to close FileChannel map to [" + persistentArrayPath + "]",e);
        }
        try {
            this.randomAccessFile.close();
        } catch (IOException e) {
            throw new RuntimeException("Unable to close RandomAccessFile [" + persistentArrayPath + "]",e);
        }
    }

    public void put(byte[] bytes, int index) {
        checkNotNull(bytes);
        checkArgument(index >= 0, "index must be greater or equal to zero");
        checkArgument(bytes.length == itemSize,"bytes length should be the same size as itemSize bytes length [" + bytes.length + "] itemsize [" + itemSize + "]");
        buffer.position( itemSize  * index);
        buffer.put(bytes);
    }

    public byte[] read(int index){
        checkArgument(index >= 0, "index must be greater or equal to zero");
        byte[] bytes = new byte[itemSize];
        buffer.position(itemSize * index);
        buffer.get(bytes);
        return bytes;
    }

}