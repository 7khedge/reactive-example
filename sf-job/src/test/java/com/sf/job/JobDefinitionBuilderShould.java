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
 * Created by adityasofat on 11/11/2015.
 */
public class JobDefinitionBuilderShould {

    @Test
    public void withReader(){
        //Given
        JobName jobName = new JobName("ApplicationInstance");
        List<JsonRecord> jsonRecordCollector = new ArrayList<>();
        List<String> stringListCollector = new ArrayList<>();
        //When
        IdKey idKey = new IdKey("id");
        JobDefinition<String,JsonRecord> jobDefinition = JobFactory.getTestJob(
                jobName,
                idKey,
                "2_ApplicationInstance.json",
                jsonRecordCollector,
                stringListCollector);
        //Then
        MatcherAssert.assertThat(jobDefinition.getJobName(), CoreMatchers.equalTo(jobName));
    }

}
