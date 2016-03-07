package com.sf.job.definition;

import com.sf.job.TestJobFactory;
import com.sf.job.domain.IdKey;
import com.sf.job.domain.JobType;
import com.sf.job.domain.JsonRecord;
import com.sf.job.parameter.DefaultJobExecutionParameters;
import com.sf.util.domain.EnumUtil;
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
        //When
        JobCollectorDefinition<String,JsonRecord> jobDefinition = TestJobFactory.getTestJob(
                JobType.simpleJsonRecord,
                new IdKey("id"),
                "2_ApplicationInstance.json",
                EnumUtil.enumParameters(DefaultJobExecutionParameters.class));
        //Then
        MatcherAssert.assertThat(jobDefinition.getJobType(), CoreMatchers.equalTo(JobType.simpleJsonRecord));
    }

}
