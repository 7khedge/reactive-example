package com.sf.jobx.item.impl;


import com.sf.jobx.domain.JsonRecord;
import com.sf.jobx.adaptor.ItemAdaptor;
import com.sf.jobx.item.ItemProcessor;

/**
 * Created by adityasofat on 27/02/2016.
 */
public class JsonRecordItemProcessor implements ItemProcessor<JsonRecord> {

    private final ItemAdaptor<JsonRecord> itemAdaptor;

    public JsonRecordItemProcessor(ItemAdaptor<JsonRecord> itemAdaptor) {
        this.itemAdaptor = itemAdaptor;
    }

    @Override
    public void process(JsonRecord item) {
        this.itemAdaptor.apply(item);
    }

}
