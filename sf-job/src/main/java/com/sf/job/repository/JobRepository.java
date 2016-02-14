package com.sf.job.repository;


import com.sf.job.domain.Job;

/**
 * Created by adityasofat on 13/02/2016.
 */
public interface JobRepository {

    Job create(String jobName, String dataKey);
}
