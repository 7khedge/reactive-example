package com.sf.chronicle.queue;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class PersistenceSpaceShould {
    //Given
    private PersistenceSpace persistenceSpace = PersistenceSpaces.defaultPersistenceSpace();

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
        assertThat(persistenceSpace.getName(),equalTo("default"));
    }

    @Test
    public void printOutSystemProperties() {
        Properties properties = System.getProperties();
        MatcherAssert.assertThat((String)properties.get("os.name"), Matchers.equalTo("Mac OS X"));
    }

    private void assertThatDirectory(boolean exists, String directoryPath) {
        File queueDirectory = new File(directoryPath);
        assertThat(queueDirectory.exists(), equalTo(exists));
    }


}