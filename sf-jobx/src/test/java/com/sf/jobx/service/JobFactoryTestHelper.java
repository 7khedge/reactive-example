package com.sf.jobx.service;

import com.sf.jobx.adaptor.ItemAdaptor;
import com.sf.jobx.domain.JobName;
import com.sf.jobx.domain.JsonRecord;

/**
 * Created by adityasofat on 15/04/16.
 */
public class JobFactoryTestHelper {

    public static JobFactory<String,JsonRecord> factoryWithSingleJob(JobName jobName, ItemAdaptor<JsonRecord> jsonRecordItemAdaptor){
        JobFactory<String,JsonRecord> jobFactory = new JobFactoryImpl();
        jobFactory.add(jobName, Jobs.SNSApplicationInstance(jsonRecordItemAdaptor));
        return jobFactory;
    }

}
