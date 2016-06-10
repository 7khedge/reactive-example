package com.sf.jobx.service;

import com.sf.jobx.config.JobConfig;
import com.sf.jobx.config.JsonRecordFileConfig;
import com.sf.jobx.domain.JobName;
import com.sf.jobx.domain.JsonRecord;
import com.sun.javafx.collections.MappingChange;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adityasofat on 07/03/2016.
 */
public class JobFactoryImpl implements JobFactory<String,JsonRecord> {

    Map<JobName,JobConfig<String,JsonRecord>> jobConfigMap = new HashMap<>();

    @Override
    public void add(JobName jobName, JobConfig<String,JsonRecord> jobConfig) {
        jobConfigMap.put(jobName,jobConfig);
    }

    @Override
    public JobConfig<String,JsonRecord> getName(JobName jobName) {
        return jobConfigMap.get(jobName);
    }
}
