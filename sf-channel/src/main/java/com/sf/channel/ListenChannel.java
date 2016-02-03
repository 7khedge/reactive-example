package com.sf.channel;

/**
 * Created by adityasofat on 02/02/2016.
 */
public interface ListenChannel<T> extends Channel {
    void onMessage(T message);
    void init();
    void close();

}
