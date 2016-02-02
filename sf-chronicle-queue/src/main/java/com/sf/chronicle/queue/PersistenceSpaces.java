package com.sf.chronicle.queue;



/**
 * Created by adityasofat on 22/01/2016.
 */
public class PersistenceSpaces {

    public static final String DEFAULT = "default";

    public static PersistenceSpace persistenceSpace(String name) {
        return persistenceSpace(false, name);
    }

    public static PersistenceSpace persistenceSpace(boolean keepDirectory, String name) {
        PersistenceSpace persistenceSpace = new PersistenceSpace(getTemporaryDirectory(), name, keepDirectory);
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
