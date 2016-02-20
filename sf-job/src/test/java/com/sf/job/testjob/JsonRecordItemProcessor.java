package com.sf.job.testjob;

import com.sf.job.ItemProcessor;
import com.sf.job.domain.JsonRecord;

import java.util.List;

/**
 * Created by adityasofat on 08/02/2016.
 */
public class JsonRecordItemProcessor implements ItemProcessor<JsonRecord> {

    private final List<JsonRecord> jsonCollector;

    public JsonRecordItemProcessor(List<JsonRecord> jsonCollector) {
        this.jsonCollector = jsonCollector;
    }

    @Override
    public void process(JsonRecord item) {
        this.jsonCollector.add(item);
    }

}
