package com.sf.job;


import java.util.Optional;

/**
 * Created by adityasofat on 18/11/2015.
 */
public interface ItemProcessor<F,T> {

    Optional<T> process(F item);

}
