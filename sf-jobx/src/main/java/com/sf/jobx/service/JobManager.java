package com.sf.jobx.service;

import com.sf.jobx.config.JobConfig;
import com.sf.jobx.domain.JsonRecord;

/**
 * Created by adityasofat on 15/04/16.
 */
public interface JobManager {
    void startJob(JobConfig<String, JsonRecord> name);
}
