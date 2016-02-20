package com.sf.job.service;

import com.sf.job.JobDefinition;
import com.sf.job.domain.Job;
import com.sf.job.domain.JobName;
import com.sf.job.repository.JobRepository;

/**
 * Created by adityasofat on 20/02/2016.
 */
public class DefaultJobService implements JobService {

    private final JobRepository jobRepository;

    public DefaultJobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job addJob(JobDefinition jobDefinition) {
        return jobRepository.create(jobDefinition.getJobName(),jobDefinition.getIdKey());
    }

    @Override
    public Job getJob(JobName jobName) {
        return jobRepository.read(jobName);
    }

}
