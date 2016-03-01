package com.sf.job.repository;


import com.sf.job.domain.IdKey;
import com.sf.job.domain.Job;
import com.sf.job.domain.JobName;
import com.sf.job.domain.JobType;

import java.util.List;

/**
 * Created by adityasofat on 13/02/2016.
 */
public interface JobRepository {

    Job create(JobName jobName, IdKey dataKey);

    Job read(JobName jobName);

    List<Job> readAllJobs();
}
