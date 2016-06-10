package com.sf.jobx.adaptor.impl;

import com.sf.jobx.item.ItemCollectingAdaptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adityasofat on 08/03/2016.
 */
public class ItemCollectingAdaptorImpl<T> implements ItemCollectingAdaptor<T> {

    private List<T> collectedItems = new ArrayList<>();

    @Override
    public List<T> getCollector() {
        return collectedItems;
    }

    @Override
    public void apply(T item) {
        this.collectedItems.add(item);
    }

    @Override
    public String toString() {
        return "ItemCollectingAdaptorImpl{" +
                "collectedItems=" + collectedItems +
                '}';
    }
}
