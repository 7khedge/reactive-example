package com.sf.jobs.spring.context;

import com.sf.job.definition.JobCollectorDefinition;
import com.sf.job.domain.Job;
import com.sf.job.domain.JobName;
import com.sf.job.repository.JobRepository;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * Created by adityasofat on 29/02/2016.
 */
public class JobFactoryImpl implements JobFactory {

    private final JobRepository jobRepository;
    private final Map<JobName, JobCollectorDefinition> jobDefinitions = new HashMap<>();
    private final Set<JobName> jobNames;

    public JobFactoryImpl(String[] jobBeans, JobRepository jobRepository) {
        this.jobRepository = jobRepository;
        this.jobNames = asList(jobBeans)
                .stream()
                .map(jobBean -> new JobName(jobBean.substring(0, 2), jobBean.substring(3)))
                .collect(toSet());
    }

    @Override
    public void add(JobName jobName, JobCollectorDefinition jobCollectorDefinition){
        this.jobDefinitions.put(jobName,jobCollectorDefinition);
    }

    @Override
    public void validateJobDefinitions() {
        syncWithJobDefinitions();
        syncWithDatabaseJobs();
    }

    private void syncWithDatabaseJobs(){
        List<JobName> databaseJobNames = this.jobRepository.readAllJobs().stream().map(Job::getJobName).collect(toList());
        List<JobName> missingDatabaseJobs = jobNames.stream().filter(jobName -> !databaseJobNames.contains(jobName)).collect(toList());
        missingDatabaseJobs.forEach( jobName -> this.jobRepository.create(jobName, jobDefinitions.get(jobName).getIdKey()));
    }

    private void syncWithJobDefinitions(){
        List<JobName> missingJobDefinitions = jobNames.stream().filter(jobName -> !jobDefinitions.containsKey(jobName)).collect(toList());
        if(missingJobDefinitions.size() > 0){
            throw new RuntimeException("Missing Job Definitions for [" + Arrays.toString(missingJobDefinitions.toArray()));
        }
    }

}
