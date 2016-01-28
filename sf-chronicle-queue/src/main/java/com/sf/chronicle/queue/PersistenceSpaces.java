package com.sf.chronicle.queue;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class PersistenceSpaces {


    public static PersistenceSpace defaultPersistenceSpace() {
        PersistenceSpace persistenceSpace = new PersistenceSpace(getTemporaryDirectory(), "default");
        persistenceSpace.createPersistenceSpace();
        return persistenceSpace;
    }

    private static String getTemporaryDirectory() {
        if ( System.getProperty("os.name").equals("Mac OS X") ) {
            return "/tmp";
        }
        return System.getProperty("java.io.tmpdir");
    }



}
