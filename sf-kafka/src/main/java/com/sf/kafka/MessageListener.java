package com.sf.kafka;

/**
 * Created by adityasofat on 21/01/2016.
 */
public interface MessageListener<V> {
    void onMessage(V message);
}
