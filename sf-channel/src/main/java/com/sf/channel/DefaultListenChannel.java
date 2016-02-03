package com.sf.channel;

/**
 * Created by adityasofat on 02/02/2016.
 */
public class DefaultListenChannel<T> implements ListenChannel<T> {

    private final String name;
    private final Adaptor<T> adaptor;

    public DefaultListenChannel(String name, Adaptor<T> adaptor) {
        this.name = name;
        this.adaptor = adaptor;
    }

    @Override
    public void onMessage(T message) {
        this.adaptor.process(message);
    }

    @Override
    public void init() {

    }

    @Override
    public void close() {

    }

    @Override
    public String getName() {
        return this.name;
    }
}
