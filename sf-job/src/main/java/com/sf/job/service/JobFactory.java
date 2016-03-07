package com.sf.job.service;

import com.sf.job.domain.Job;
import com.sf.job.domain.JobName;
import com.sf.job.runner.JobRunner;

/**
 * Created by adityasofat on 29/02/2016.
 */
public interface JobFactory {

    Job getJob(JobName  jobName);

    void add(JobName jobName, JobRunner jobDefinition);

    JobRunner getJobRunner(JobName jobName);
}
