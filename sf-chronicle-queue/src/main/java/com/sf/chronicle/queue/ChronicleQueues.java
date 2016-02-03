package com.sf.chronicle.queue;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class ChronicleQueues {


    public static <T> ChronicleQueue newQueue(String channelName, PersistenceSpace persistenceSpace) {
        ChronicleQueue<T> chronicleQueue = new ChronicleQueue<T>(channelName, persistenceSpace);
        chronicleQueue.init();
        return chronicleQueue;
    }

    public static <T> ChronicleQueue newObservableQueue(String channelName, PersistenceSpace persistenceSpace) {
        ChronicleQueue<T> chronicleQueue = new ChronicleQueue<T>(channelName, persistenceSpace);
        chronicleQueue.init();


        return chronicleQueue;
    }


}
