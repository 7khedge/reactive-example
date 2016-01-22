package com.sf.channel;

import com.sf.chronicle.queue.PersistenceSpace;
import com.sf.chronicle.queue.PersistenceSpaces;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class ChannelShould {
    private PersistenceSpace persistenceSpace;

    @Before
    public void setup() {
        persistenceSpace = PersistenceSpaces.defaultPersistenceSpace();
    }

    @After
    public void tearDown() {
        persistenceSpace.removePersistenceSpace();
    }

    @Test
    public void publishToChannel() {
        //Given
        List<String> collector = new ArrayList<String>();
        Channel<String> stringChannel = Channels.newStringChannel("reader",collector,persistenceSpace);
    }

}