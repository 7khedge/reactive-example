package com.sf.job.item.impl;

import com.sf.job.domain.JsonRecord;
import com.sf.job.item.ItemCollectorProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adityasofat on 27/02/2016.
 */
public class JsonRecordItemCollectorProcessor implements ItemCollectorProcessor<JsonRecord> {

    private List<JsonRecord> jsonRecords = new ArrayList<>();

    @Override
    public List<JsonRecord> getCollector() {
        return new ArrayList<>(jsonRecords);
    }

    @Override
    public void process(JsonRecord item) {
        this.jsonRecords.add(item);
    }


}
