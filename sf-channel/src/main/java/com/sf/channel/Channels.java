package com.sf.channel;

import com.sf.chronicle.queue.ChronicleQueues;
import com.sf.chronicle.queue.PersistenceSpaces;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class Channels {


    public static Channel newStringChannel(String channelName) {
        return new StringChannel(new StringAdaptor(), ChronicleQueues.newQueue(channelName, PersistenceSpaces.defaultPersistenceSpace()));
    }
}
