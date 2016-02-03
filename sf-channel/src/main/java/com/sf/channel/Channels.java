package com.sf.channel;

import com.sf.chronicle.queue.ChronicleQueue;
import com.sf.chronicle.queue.ChronicleQueues;
import com.sf.chronicle.queue.PersistenceSpaces;

import static com.sf.chronicle.queue.PersistenceSpaces.DEFAULT;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class Channels {

    public static ListenChannel<String> newListenChannel(String channelName) {
        ChronicleQueue chronicleQueue = ChronicleQueues.newQueue(channelName, PersistenceSpaces.persistenceSpace(DEFAULT));
        return new DefaultListenChannel<String>(channelName, new StringAdaptor());
    }

    public static PublishChannel<String> newPublishChannel(String channelName) {
        return new DefaultPublishChannel<String>(channelName, ChronicleQueues.newQueue(channelName, PersistenceSpaces.persistenceSpace(DEFAULT)), 128);
    }

}
