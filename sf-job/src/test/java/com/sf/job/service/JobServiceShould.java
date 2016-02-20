package com.sf.job.service;

import com.sf.datasource.DataSourceUtil;
import com.sf.datasource.TruncateUtil;
import com.sf.job.JobDefinition;
import com.sf.job.JobDefinitionBuilder;
import com.sf.job.domain.Job;
import com.sf.job.domain.JsonRecord;
import com.sf.job.repository.jdbc.JobJdbcRepository;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by adityasofat on 20/02/2016.
 */
public class JobServiceShould {

    @Before
    public void clearDownTables(){
        TruncateUtil truncateUtil = new TruncateUtil(DataSourceUtil.simpleDatSource());
        truncateUtil.truncateAllTables("jobExecution","job");
    }


    @Test
    public void addAJob(){
        //Given
        JobService jobService = new DefaultJobService(new JobJdbcRepository(DataSourceUtil.simpleDatSource()));
        JobDefinition<String, JsonRecord> testJob = getTestJob(new TestJobConfig());
        //When
        Job createdJob = jobService.addJob(testJob);
        //Then
        Job retrievedJob = jobService.getJob(testJob.getJobName());
        MatcherAssert.assertThat(createdJob,CoreMatchers.equalTo(retrievedJob));
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