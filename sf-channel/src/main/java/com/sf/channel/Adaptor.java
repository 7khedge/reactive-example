package com.sf.channel;

/**
 * Created by adityasofat on 22/01/2016.
 */
public interface Adaptor<T> {
    T process(T t);
}
