package com.sf.chronicle.queue;

import org.junit.After;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class PersistenceSpaceShould {
    //Given
    private PersistenceSpace persistenceSpace = new PersistenceSpace(System.getProperty("java.io.tmpdir"), "eventQueue");

    @After
    public void tearDown() {
        File queueDirectory = new File(persistenceSpace.getPath());
        if ( queueDirectory.exists()) {
            queueDirectory.delete();
        }
    }

    @Test
    public void createPersistenceSpace() throws Exception {
        //When
        persistenceSpace.createPersistenceSpace();
        //Then
        assertThatDirectory(true, persistenceSpace.getPath());
    }

    @Test
    public void removePersistenceSpace() throws Exception {
        //Given
        persistenceSpace.createPersistenceSpace();
        //When
        persistenceSpace.removePersistenceSpace();
        //Then
        assertThatDirectory(false, persistenceSpace.getPath());
    }

    @Test
    public void returnPersistenceSpaceName() throws Exception {
        //When//Then
        assertThat(persistenceSpace.getName(),equalTo("eventQueue"));
    }

    private void assertThatDirectory(boolean exists, String directoryPath) {
        File queueDirectory = new File(directoryPath);
        assertThat(queueDirectory.exists(), equalTo(exists));
    }


}