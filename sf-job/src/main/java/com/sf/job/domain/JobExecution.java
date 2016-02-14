package com.sf.job.domain;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * Created by adityasofat on 13/02/2016.
 */
public class JobExecution {
    private final Long jobExecutionId;
    private final Long jobId;
    private final Map<String,String> propeties;
    private final LocalDateTime startDateTime;
    private final Optional<LocalDateTime> stopDateTime;
    private final JobExecutionStatus status;
    private final Optional<String> exitMessage;

    public JobExecution(Long jobExecutionId, Long jobId, Map<String, String> properties, LocalDateTime startDateTime) {
        this.jobExecutionId = jobExecutionId;
        this.jobId = jobId;
        this.propeties = properties;
        this.startDateTime = startDateTime;
        this.stopDateTime = Optional.empty();
        this.status = JobExecutionStatus.RUNNING;
        this.exitMessage = Optional.empty();
    }

}
