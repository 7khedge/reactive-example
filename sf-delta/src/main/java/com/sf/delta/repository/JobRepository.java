package com.sf.delta.repository;

import com.sf.delta.domain.Job;

/**
 * Created by adityasofat on 13/02/2016.
 */
public interface JobRepository {

    Job create(String jobName, String dataKey);
}
