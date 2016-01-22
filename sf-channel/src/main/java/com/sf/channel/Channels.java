package com.sf.channel;

import com.sf.chronicle.queue.ChronicleQueues;
import com.sf.chronicle.queue.PersistenceSpace;
import com.sf.chronicle.queue.PersistenceSpaces;

import java.util.List;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class Channels {


    public static Channel newStringChannel(String channelName, List<String> collector, PersistenceSpace persistenceSpace) {
        StringChannel stringChannel = new StringChannel(channelName, new StringAdaptor(collector), ChronicleQueues.newQueue(channelName, persistenceSpace));
        stringChannel.init();
        return stringChannel;
    }
}
