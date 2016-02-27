package com.sf.job.definition;

import com.sf.job.JobFactory;
import com.sf.job.definition.JobDefinition;
import com.sf.job.domain.IdKey;
import com.sf.job.domain.JobType;
import com.sf.job.domain.JsonRecord;
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
        IdKey idKey = new IdKey("id");
        JobCollectorDefinition<String,JsonRecord> jobDefinition = JobFactory.getTestJob(
                JobType.simpleJsonRecord,
                idKey,
                "2_ApplicationInstance.json");
        //Then
        MatcherAssert.assertThat(jobDefinition.getJobType(), CoreMatchers.equalTo(JobType.simpleJsonRecord));
    }

}
