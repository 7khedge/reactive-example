package com.sf.job.domain;

/**
 * Created by adityasofat on 13/02/2016.
 */
public class Job {
    private final Long jobId;
    private final String jobName;
    private final String dataKey;

    public Job(Long jobId, String jobName, String dataKey) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.dataKey = dataKey;
    }

    public Long getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public String getDataKey() {
        return dataKey;
    }
}
