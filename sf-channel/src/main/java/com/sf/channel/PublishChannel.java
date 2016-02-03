package com.sf.channel;

/**
 * Created by adityasofat on 02/02/2016.
 */
public interface PublishChannel<T> extends Channel {
    void publish(T message);
}
