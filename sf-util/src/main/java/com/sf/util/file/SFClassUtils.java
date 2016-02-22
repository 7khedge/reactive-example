package com.sf.util.file;

/**
 * Created by adityasofat on 22/02/2016.
 */
public class SFClassUtils {

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}
