package com.sf.jobx.service;

import com.sf.jobx.config.JsonRecordFileConfig;
import com.sf.jobx.domain.JobName;

/**
 * Created by adityasofat on 07/03/2016.
 */
public interface JobFactory {

    void add(JobName jobName, JsonRecordFileConfig jsonRecordFileConfig);
}
