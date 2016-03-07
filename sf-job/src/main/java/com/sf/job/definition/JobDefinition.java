package com.sf.job.definition;

import com.sf.job.domain.IdKey;
import com.sf.job.domain.JobType;

import java.util.Map;

/**
 * Created by adityasofat on 03/03/2016.
 */
public interface JobDefinition {
    JobType getJobType();

    IdKey getIdKey();

    Map<String,Integer> getJobExecutionParameters();
}
