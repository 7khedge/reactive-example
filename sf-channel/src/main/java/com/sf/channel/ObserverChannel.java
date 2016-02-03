package com.sf.channel;

/**
 * Created by adityasofat on 03/02/2016.
 */
public class ObserverChannel<T> implements ListenChannel<T> {

    @Override
    public void onMessage(T message) {

    }

    @Override
    public void init() {

    }

    @Override
    public void close() {

    }

    @Override
    public String getName() {
        return null;
    }
}
