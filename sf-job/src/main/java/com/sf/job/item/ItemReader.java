package com.sf.job.item;


/**
 * Created by adityasofat on 11/11/2015.
 */
public interface ItemReader<F,T> {

    T readItem(F f);

}
