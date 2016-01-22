package com.sf.chronicle.queue;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class ChronicleQueuesShould {

    @Test
    public void createNameChronicleQueue(){
        //Given
        String channelName = "reader";
        //When
        ChronicleQueue chronicleQueue = ChronicleQueues.newQueue(channelName, PersistenceSpaces.defaultPersistenceSpace());
        //Then
        MatcherAssert.assertThat(chronicleQueue.getQueueName(), CoreMatchers.equalTo(channelName));
    }

}