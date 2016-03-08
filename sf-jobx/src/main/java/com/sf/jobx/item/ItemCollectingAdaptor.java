package com.sf.jobx.item;

import com.sf.jobx.adaptor.ItemAdaptor;

import java.util.List;

/**
 * Created by adityasofat on 08/03/2016.
 */
public interface ItemCollectingAdaptor<T> extends ItemAdaptor<T> {

    List<T> getCollector();
}
