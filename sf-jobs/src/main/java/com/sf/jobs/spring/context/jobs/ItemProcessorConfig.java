package com.sf.jobs.spring.context.jobs;

import com.sf.job.item.impl.JsonRecordItemCollectorProcessor;

public class ItemProcessorConfig {

    public static JsonRecordItemCollectorProcessor jsonRecordItemCollectorProcessor() {
        return new JsonRecordItemCollectorProcessor();
    }

}
