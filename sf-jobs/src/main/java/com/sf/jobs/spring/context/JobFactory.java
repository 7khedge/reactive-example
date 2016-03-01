package com.sf.jobs.spring.context;

import com.sf.job.definition.JobCollectorDefinition;
import com.sf.job.domain.JobName;

/**
 * Created by adityasofat on 29/02/2016.
 */
public interface JobFactory {


    void add(JobName jobName, JobCollectorDefinition jobCollectorDefinition);

    void validateJobDefinitions();
}
