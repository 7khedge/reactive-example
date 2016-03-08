package com.sf.jobx.item.impl;

import com.sf.jobx.domain.JsonRecord;
import com.sf.jobx.adaptor.ItemAdaptor;
import com.sf.jobx.item.ItemWriter;

/**
 * Created by adityasofat on 08/02/2016.
 */
public class JsonRecordItemWriter implements ItemWriter<JsonRecord> {

    private final ItemAdaptor<JsonRecord> itemAdaptor;

    public JsonRecordItemWriter(ItemAdaptor<JsonRecord> itemAdaptor) {
        this.itemAdaptor = itemAdaptor;
    }

    @Override
    public void write(JsonRecord item) {
        this.itemAdaptor.apply(item);
    }

}
