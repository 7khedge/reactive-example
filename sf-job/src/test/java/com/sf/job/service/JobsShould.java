package com.sf.job.service;

import com.sf.job.JobDefinition;
import com.sf.job.JobDefinitionBuilder;
import com.sf.job.testjob.JsonRecord;
import org.junit.Test;

/**
 * Created by adityasofat on 20/02/2016.
 */
public class JobsShould {

    @Test
    public void addAJob(){
        getTestJob(new TestJobConfig());
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