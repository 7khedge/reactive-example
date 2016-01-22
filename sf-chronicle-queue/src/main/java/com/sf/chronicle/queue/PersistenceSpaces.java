package com.sf.chronicle.queue;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class PersistenceSpaces {


    public static PersistenceSpace defaultPersistenceSpace() {
        PersistenceSpace persistenceSpace = new PersistenceSpace(System.getProperty("java.io.tmpdir"), "default");
        persistenceSpace.createPersistenceSpace();
        return persistenceSpace;
    }
}
