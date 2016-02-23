package com.sf.job.service;

import com.sf.job.domain.*;
import com.sf.util.datasource.DataSourceUtil;
import com.sf.util.datasource.TruncateUtil;
import com.sf.job.JobDefinition;
import com.sf.job.JobDefinitionBuilder;
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
        Job createdJob = jobService.addJob(testJob);
        //Then
        Job retrievedJob = jobService.getJob(testJob.getJobName());
        MatcherAssert.assertThat(createdJob,CoreMatchers.equalTo(retrievedJob));
    }

    @Test
    public void startJob(){
        //Given
        jobService.addJob(testJob);
        //When
        JobExecution jobExecution = jobService.startJob(SNS_ApplicationInstance,testJob.getJobExecutionParameters());
        //Then
        MatcherAssert.assertThat(jobExecution.getStatus(),CoreMatchers.equalTo(JobExecutionStatus.RUNNING));
    }

    private JobDefinition<String,JsonRecord> getTestJob(JobConfig<String,JsonRecord> jobConfig) {
        return JobDefinitionBuilder.<String, JsonRecord>jobDefinition()
                .name(jobConfig.getName())
                .idKey(jobConfig.getId())
                .observableItems(jobConfig.getObservableItems())
                .itemReader(jobConfig.getItemReader())
                .itemProcessor(jobConfig.getItemProcessor())
                .itemWriter(jobConfig.getItemWrite())
                .jobExecutionParameters(jobConfig.getParameters())
                .build();
    }

}