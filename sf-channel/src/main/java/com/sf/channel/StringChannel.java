package com.sf.channel;

import com.sf.chronicle.queue.ChronicleQueue;
import com.sf.chronicle.queue.Message;
import com.sf.chronicle.queue.MessageListener;


/**
 * Created by adityasofat on 22/01/2016.
 */
public class StringChannel implements Channel<String> {

    private final String name;
    private final Adaptor<String> adaptor;
    private final ChronicleQueue queue;
    private final int messageSize = 128;

    public StringChannel(String name, final Adaptor<String> adaptor, ChronicleQueue queue) {
        this.name = name;
        this.adaptor = adaptor;
        this.queue = queue;
    }


    @Override
    public void init() {
        this.queue.readMessages(new MessageListener<String>() {
            @Override
            public void onMessage(Message<String> message) {
                adaptor.process(message.getPayload());
            }
        },-1);
    }

    @Override
    public void close() {
        this.queue.close();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void publish(String message) {
        this.queue.publishMessage(new Message<String>(message),messageSize);
    }

}
