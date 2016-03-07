package com.sf.job.service;

import com.sf.job.definition.JobDefinition;
import com.sf.job.domain.Job;
import com.sf.job.domain.JobName;
import com.sf.job.repository.JobRepository;
import com.sf.job.runner.JobRunner;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * Created by adityasofat on 29/02/2016.
 */
public class JobFactoryImpl implements JobFactory {

    private final JobRepository jobRepository;
    private final Map<JobName, JobDefinition> jobDefinitions = new HashMap<>();
    private final Map<JobName, JobRunner> jobRunner = new HashMap<>();
    private final Set<JobName> jobNames;
    private Map<JobName, Job> jobs;

    public JobFactoryImpl(String[] jobBeans, JobRepository jobRepository) {
        this.jobRepository = jobRepository;
        this.jobNames = asList(jobBeans).stream().map(JobName::new).collect(toSet());
    }

    public void init(){
        this.jobs = jobRepository.readAllJobs().stream().collect(Collectors.toMap(Job::getJobName, job -> job));
        validateJobDefinitions();
    }

    @Override
    public Job getJob(JobName jobName) {
        return jobs.get(jobName);
    }

    @Override
    public void add(JobName jobName, JobRunner jobRunner) {
        this.jobRunner.put(jobName, jobRunner);
    }

    public void validateJobDefinitions() {
        syncWithJobDefinitions();
        syncWithDatabaseJobs();
    }

    @Override
    public JobRunner getJobRunner(JobName jobName) {
        return null;
    }

    private void syncWithDatabaseJobs(){
        List<JobName> missingDatabaseJobs = jobNames.stream().filter(jobName -> !jobs.keySet().contains(jobName)).collect(toList());
        missingDatabaseJobs.forEach( jobName -> this.jobRepository.create(jobName, jobDefinitions.get(jobName).getIdKey()));
    }

    private void syncWithJobDefinitions(){
        List<JobName> missingJobDefinitions = jobNames.stream().filter(jobName -> !jobDefinitions.containsKey(jobName)).collect(toList());
        if(missingJobDefinitions.size() > 0){
            throw new RuntimeException("Missing Job Definitions for [" + Arrays.toString(missingJobDefinitions.toArray()));
        }
    }
}
