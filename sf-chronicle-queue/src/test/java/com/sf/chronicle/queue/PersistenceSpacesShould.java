package com.sf.chronicle.queue;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class PersistenceSpacesShould {

    @Test
    public void createDefaultPersistenceSpace(){
        //Given
        String persistenceSpaceName  = "reader";
        //When
        PersistenceSpace persistenceSpace  = PersistenceSpaces.defaultPersistenceSpace();
        //Then
        MatcherAssert.assertThat(persistenceSpace.getName(), CoreMatchers.equalTo("default"));
    }

}