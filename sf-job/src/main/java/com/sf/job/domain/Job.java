package com.sf.job.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return Objects.equals(jobId, job.jobId) &&
                Objects.equals(jobName, job.jobName) &&
                Objects.equals(dataKey, job.dataKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, jobName, dataKey);
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", jobName=" + jobName +
                ", dataKey=" + dataKey +
                '}';
    }
}
