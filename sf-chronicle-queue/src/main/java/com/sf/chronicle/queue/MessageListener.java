package com.sf.chronicle.queue;

/**
 * Created by adityasofat on 21/01/2016.
 */
public interface MessageListener<T> {
    void onMessage(Message<T> message);
}
