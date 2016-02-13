package com.sf.job.testjob;

import com.sf.job.ItemWriter;

import java.util.Map;

/**
 * Created by adityasofat on 08/02/2016.
 */
public class JsonRecordItemWriter implements ItemWriter<JsonRecord> {

    @Override
    public void write(JsonRecord item) {
        System.out.println(item.getPayload());
    }

}
