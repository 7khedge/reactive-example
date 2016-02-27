package com.sf.job.service;

import com.sf.job.domain.*;
import com.sf.util.datasource.DataSourceUtil;
import com.sf.util.datasource.TruncateUtil;
import com.sf.job.definition.JobDefinition;
import com.sf.job.definition.JobDefinitionBuilder;
import com.sf.job.repository.jdbc.JobExecutionJdbcRepository;
import com.sf.job.repository.jdbc.JobJdbcRepository;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static com.sf.job.domain.JobName.SNS_ApplicationInstance;

/**
 * Created by adityasofat on 20/02/2016.
 */
public class JobServiceShould {

    private JobService jobService = new DefaultJobService(
            new JobJdbcRepository(DataSourceUtil.simpleDatSource()),
            new JobExecutionJdbcRepository(DataSourceUtil.simpleDatSource()));
    private JobDefinition<String, JsonRecord> testJob = getTestJob(new TestJobConfig());

    @Before
    public void clearDownTables(){
        TruncateUtil truncateUtil = new TruncateUtil(DataSourceUtil.simpleDatSource());
        truncateUtil.truncateAllTables();
    }

    @Test
    public void addAJob(){
        //Given
        //When
        Job createdJob = jobService.addJob(JobName.SNS_ApplicationInstance, testJob);
        //Then
        Job retrievedJob = jobService.getJob(JobName.SNS_ApplicationInstance);
        MatcherAssert.assertThat(createdJob,CoreMatchers.equalTo(retrievedJob));
    }

    @Test
    public void startJob(){
        //Given
        jobService.addJob(JobName.SNS_ApplicationInstance,testJob);
        //When
        JobExecution jobExecution = jobService.startJob(SNS_ApplicationInstance,testJob.getJobExecutionParameters());
        //Then
        MatcherAssert.assertThat(jobExecution.getStatus(),CoreMatchers.equalTo(JobExecutionStatus.RUNNING));
    }

    private JobDefinition<String,JsonRecord> getTestJob(JobConfig<String,JsonRecord> jobConfig) {
        return JobDefinitionBuilder.<String, JsonRecord>jobDefinition()
                .jobType(jobConfig.getName())
                .idKey(jobConfig.getId())
                .observableItems(jobConfig.getObservableItems())
                .itemReader(jobConfig.getItemReader())
                .itemProcessor(jobConfig.getItemProcessor())
                .itemWriter(jobConfig.getItemWrite())
                .jobExecutionParameters(jobConfig.getParameters())
                .build();
    }

}