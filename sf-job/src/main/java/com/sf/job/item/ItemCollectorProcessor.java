package com.sf.job.item;

import java.util.List;

/**
 * Created by adityasofat on 08/02/2016.
 */
public interface ItemCollectorProcessor<T> extends ItemProcessor<T> {

    List<T> getCollector();

}
