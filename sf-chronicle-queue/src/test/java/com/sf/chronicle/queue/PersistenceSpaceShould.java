package com.sf.chronicle.queue;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.util.Properties;

import static com.sf.chronicle.queue.PersistenceSpaces.DEFAULT;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class PersistenceSpaceShould {
    //Given
    private PersistenceSpace persistenceSpace = PersistenceSpaces.persistenceSpace(false, DEFAULT);

    @After
    public void tearDown() {
        persistenceSpace.removePersistenceSpace();
    }


    @Test
    public void removeOnlyQueueFiles()  {
        //Given
        PersistenceSpace persistenceSpace = PersistenceSpaces.persistenceSpace(true, "doNotRemove");
        //When
        persistenceSpace.removePersistenceSpace();
        //Then
        assertThatDirectory(true, persistenceSpace.getPath());
    }

    @Test
    public void createPersistenceSpace() throws Exception {
        //When

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