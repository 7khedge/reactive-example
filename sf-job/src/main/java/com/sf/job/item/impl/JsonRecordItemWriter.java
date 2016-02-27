package com.sf.job.item.impl;

import com.sf.job.domain.JsonRecord;
import com.sf.job.item.ItemCollectorWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adityasofat on 08/02/2016.
 */
public class JsonRecordItemWriter implements ItemCollectorWriter<JsonRecord,String> {

    private final List<String> stringCollector = new ArrayList<>();

    public JsonRecordItemWriter() {
    }

    @Override
    public void write(JsonRecord item) {
        this.stringCollector.add(item.toString());
    }

    @Override
    public List<String> getCollector() {
        return new ArrayList<>(stringCollector);
    }


}
