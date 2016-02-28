package com.sf.jobs.spring.context.jobs;

import com.sf.job.item.impl.JsonRecordItemWriter;

/**
 * Created by adityasofat on 28/02/2016.
 */
public class ItemWriteConfig {

    public static JsonRecordItemWriter jsonRecordItemWriter(){
        return new JsonRecordItemWriter();
    }

}
