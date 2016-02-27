package com.sf.job.item;

import java.util.List;

/**
 * Created by adityasofat on 08/02/2016.
 */
public interface ItemCollectorWriter<T,F> extends ItemWriter<T> {

    List<F> getCollector();

}
