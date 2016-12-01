package com.sf.data.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Created by adityasofat on 01/12/2016.
 */
public class FileProcessor {

    public FileProcessor(){
    }

    public void processFile(Path path,MessageListener messageListener){
        try {
            Files.lines(path)
                    .parallel()
                    .forEach(messageListener::onMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
