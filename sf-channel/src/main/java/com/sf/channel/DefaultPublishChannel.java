package com.sf.channel;

import com.sf.chronicle.queue.ChronicleQueue;
import com.sf.chronicle.queue.Message;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class DefaultPublishChannel<T> implements PublishChannel<T> {

    private final String name;
    private final ChronicleQueue<T> queue;
    private final int messageSize;

    public DefaultPublishChannel(String name, ChronicleQueue<T> queue, int messageSize) {
        this.name = name;
        this.queue = queue;
        this.messageSize = messageSize;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void publish(T message) {
        queue.publishMessage(new Message<T>(message), messageSize);
    }
}
