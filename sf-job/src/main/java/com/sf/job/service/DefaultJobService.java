package com.sf.job.service;

import com.sf.job.domain.Job;
import com.sf.job.domain.JobExecution;
import com.sf.job.domain.JobName;
import com.sf.job.repository.JobExecutionRepository;
import com.sf.job.runner.JobRunner;

import java.util.Map;

/**
 * Created by adityasofat on 20/02/2016.
 */
public class DefaultJobService implements JobService {

    private final JobFactory jobFactory;
    private final JobExecutionRepository jobExecutionRepository;

    public DefaultJobService(JobFactory jobFactory, JobExecutionRepository jobExecutionRepository) {
        this.jobFactory = jobFactory;
        this.jobExecutionRepository = jobExecutionRepository;
    }

    @Override
    public JobExecution startJob(JobName jobName, Map<String,Integer> properties) {
        Job job = jobFactory.getJob(jobName);
        JobExecution jobExecution = this.jobExecutionRepository.create(job.getJobId(), properties);
        JobRunner jobRunner = jobFactory.getJobRunner(jobName);
        jobRunner.startJob();
        return jobExecution;
    }

}
