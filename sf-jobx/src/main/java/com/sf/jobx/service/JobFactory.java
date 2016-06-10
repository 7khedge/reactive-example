package com.sf.jobx.service;

import com.sf.jobx.config.JobConfig;
import com.sf.jobx.config.JsonRecordFileConfig;
import com.sf.jobx.domain.JobName;

/**
 * Created by adityasofat on 07/03/2016.
 */
public interface JobFactory<F,T> {

    void add(JobName jobName, JobConfig<F,T> jobConfig);

    JobConfig<F,T> getName(JobName jobName);
}
