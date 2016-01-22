package com.sf.chronicle.queue;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class ChronicleQueues {


    public static ChronicleQueue newQueue(String channelName, PersistenceSpace persistenceSpace) {
        ChronicleQueue chronicleQueue = new ChronicleQueue(channelName, persistenceSpace);
        chronicleQueue.init();
        return chronicleQueue;
    }
}
