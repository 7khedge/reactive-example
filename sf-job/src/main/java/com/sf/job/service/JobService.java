package com.sf.job.service;

import com.sf.job.domain.JobExecution;
import com.sf.job.domain.JobName;

import java.util.Map;

/**
 * Created by adityasofat on 20/02/2016.
 */
public interface JobService {

    JobExecution startJob(JobName jobName, Map<String,Integer> properties);
}
