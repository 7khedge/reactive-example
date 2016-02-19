package com.sf.job.testjob;

import com.sf.job.ItemWriter;

import java.util.List;
import java.util.Map;

/**
 * Created by adityasofat on 08/02/2016.
 */
public class JsonRecordItemWriter implements ItemWriter<JsonRecord> {

    private final List<String> stringCollector;

    public JsonRecordItemWriter(List<String> stringCollector) {

        this.stringCollector = stringCollector;
    }

    @Override
    public void write(JsonRecord item) {
        this.stringCollector.add(item.toString());
    }

}
