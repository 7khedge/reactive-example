package com.sf.channel;

/**
 * Created by adityasofat on 22/01/2016.
 */
public interface Channel<T> {


    String getName();
    void init();
    void close();
    void publish(T message);

}
