package com.sf.job;

import com.sf.job.domain.IdKey;
import com.sf.job.domain.JobName;
import com.sf.job.domain.JsonRecord;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adityasofat on 10/02/2016.
 */
public class JobRunnerShould {

    @Test
    public void runStartJob(){
        //Given
        List<JsonRecord> jsonRecordCollector = new ArrayList<>();
        List<String> stringListCollector = new ArrayList<>();
        JobDefinition<String,JsonRecord> jobDefinition = JobFactory.getTestJob(JobName.SNS_ApplicationInstance,
                new IdKey("id"),
                "2_ApplicationInstance.json",
                jsonRecordCollector,
                stringListCollector);
        JobRunner<String,JsonRecord> jobRunner = new JobRunner<>(jobDefinition);
        //When
        jobRunner.startJob();
        //Then
        MatcherAssert.assertThat(jsonRecordCollector.size(), CoreMatchers.equalTo(10));
        MatcherAssert.assertThat(stringListCollector.size(), CoreMatchers.equalTo(10));

    }


}