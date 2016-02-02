package com.sf.chronicle.queue;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static com.sf.chronicle.queue.PersistenceSpaces.DEFAULT;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class ChronicleQueuesShould {

    @Test
    public void createNameChronicleQueue(){
        //Given
        String channelName = "reader";
        //When
        ChronicleQueue chronicleQueue = ChronicleQueues.newQueue(channelName, PersistenceSpaces.persistenceSpace(false, DEFAULT));
        //Then
        MatcherAssert.assertThat(chronicleQueue.getQueueName(), CoreMatchers.equalTo(channelName));
    }

}