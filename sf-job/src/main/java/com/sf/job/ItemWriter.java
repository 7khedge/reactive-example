package com.sf.job;

/**
 * Created by adityasofat on 20/11/2015.
 */
public interface ItemWriter<T> {

    void write(T item) throws Exception;
}
