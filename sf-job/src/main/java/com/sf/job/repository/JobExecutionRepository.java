package com.sf.job.repository;

import com.sf.job.domain.JobExecution;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by adityasofat on 14/02/2016.
 */
public interface JobExecutionRepository {

    JobExecution create(Long jobId, Map<String, Integer> properties);
}
