package com.sf.job;

import com.sf.job.testjob.*;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Created by adityasofat on 11/11/2015.
 */
public class JobDefinitionBuilderShould {

    @Test
    public void withReader(){
        //Given
        String jobName = "ApplicationInstance";
        //When
        IdKey idKey = new IdKey("id");
        JobDefinition<String,JsonRecord,DefaultJobExecutionParameters> jobDefinition = JobFactory.getTestJob(jobName,idKey,"2_ApplicationInstance.json");
        //Then
        MatcherAssert.assertThat(jobDefinition.getJobName(), CoreMatchers.equalTo(jobName));
    }

}
