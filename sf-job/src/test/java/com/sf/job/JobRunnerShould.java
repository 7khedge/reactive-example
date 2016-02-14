package com.sf.job;

import com.sf.job.testjob.DefaultJobExecutionParameters;
import com.sf.job.testjob.JsonRecord;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Created by adityasofat on 10/02/2016.
 */
public class JobRunnerShould {

    @Test
    public void runStartJob(){
        //Given
        JobDefinition<String,JsonRecord,DefaultJobExecutionParameters> jobDefinition = JobFactory.getTestJob("ApplicationInstance",new IdKey("id"),"2_ApplicationInstance.json");
        JobRunner<String,JsonRecord,DefaultJobExecutionParameters> jobRunner = new JobRunner<>(jobDefinition);
        //When
        jobRunner.startJob();
        //Then
        //MatcherAssert.assertThat();

    }


}