package com.sf.job.testjob;

import com.sf.job.ItemProcessor;

import java.util.Map;
import java.util.Optional;

/**
 * Created by adityasofat on 08/02/2016.
 */
public class JsonRecordItemProcessor implements ItemProcessor<JsonRecord> {



    @Override
    public void process(JsonRecord item) {

        System.out.println(item);
    }

}
