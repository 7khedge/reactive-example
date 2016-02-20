package com.sf.job.domain;

/**
 * Created by adityasofat on 13/02/2016.
 */
public class Job {
    private final Long jobId;
    private final JobName jobName;
    private final IdKey dataKey;

    public Job(Long jobId, JobName jobName, IdKey dataKey) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.dataKey = dataKey;
    }

    public Long getJobId() {
        return jobId;
    }

    public JobName getJobName() {
        return jobName;
    }

    public IdKey getDataKey() {
        return dataKey;
    }
}
