package com.sf.jobs.spring.context.jobs;

import com.sf.job.domain.IdKey;
import com.sf.job.item.impl.JsonRecordItemReader;

/**
 * Created by adityasofat on 28/02/2016.
 */
public class ItemReaderConfig {

    public static JsonRecordItemReader jsonRecordItemReader(IdKey idKey){
        return new JsonRecordItemReader(idKey);
    }
}
