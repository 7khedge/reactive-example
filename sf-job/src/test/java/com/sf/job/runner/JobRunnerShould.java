package com.sf.job.runner;

import com.sf.job.TestJobFactory;
import com.sf.job.definition.JobCollectorDefinition;
import com.sf.job.domain.IdKey;
import com.sf.job.domain.JobType;
import com.sf.job.domain.JsonRecord;
import com.sf.job.parameter.DefaultJobExecutionParameters;
import com.sf.util.domain.EnumUtil;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Created by adityasofat on 10/02/2016.
 */
public class JobRunnerShould {

    @Test
    public void runStartJob(){
        //Given
        JobCollectorDefinition<String,JsonRecord> jobDefinition = TestJobFactory.getTestJob(JobType.simpleJsonRecord,
                new IdKey("id"),
                "2_ApplicationInstance.json",
                EnumUtil.enumParameters(DefaultJobExecutionParameters.class));
        JobCollectorRunner<String,JsonRecord> jobCollectorRunner = new JobCollectorRunner<>(jobDefinition);
        //When
        jobCollectorRunner.startJob();
        //Then
        MatcherAssert.assertThat(jobDefinition.getItemCollectorProcessor().getCollector().size(), CoreMatchers.equalTo(10));
        MatcherAssert.assertThat(jobDefinition.getItemCollectorWriter().getCollector().size(), CoreMatchers.equalTo(10));

    }


}