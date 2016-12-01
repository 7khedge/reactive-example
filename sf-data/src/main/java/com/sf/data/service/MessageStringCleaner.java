package com.sf.data.service;

/**
 * Created by adityasofat on 01/12/2016.
 */
public class MessageStringCleaner {

    private final static String nullString = "\\N";


    public static String cleanString(String string){
        if (string == null || string.isEmpty() || string.equals(nullString)) {
            return "";
        }
        if (string.charAt(0) == '"' && string.charAt(string.length()-1) == '"'){
            return string.substring(1, string.length() - 1);
        }
        return string;
    }

}
