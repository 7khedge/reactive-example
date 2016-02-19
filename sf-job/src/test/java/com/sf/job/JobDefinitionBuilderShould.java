package com.sf.job;

import com.sf.job.testjob.*;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adityasofat on 11/11/2015.
 */
public class JobDefinitionBuilderShould {

    @Test
    public void withReader(){
        //Given
        String jobName = "ApplicationInstance";
        List<JsonRecord> jsonRecordCollector = new ArrayList<>();
        List<String> stringListCollector = new ArrayList<>();
        //When
        IdKey idKey = new IdKey("id");
        JobDefinition<String,JsonRecord,DefaultJobExecutionParameters> jobDefinition = JobFactory.getTestJob(
                jobName,
                idKey,
                "2_ApplicationInstance.json",
                jsonRecordCollector,
                stringListCollector);
        //Then
        MatcherAssert.assertThat(jobDefinition.getJobName(), CoreMatchers.equalTo(jobName));
    }

}
