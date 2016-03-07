package com.sf.jobs.spring.context;

import com.sf.job.definition.JobCollectorDefinition;
import com.sf.job.domain.IdKey;
import com.sf.job.domain.JsonRecord;
import com.sf.job.parameter.DefaultJobExecutionParameters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static com.sf.jobs.spring.context.jobs.ItemObservable.connectableObservable;
import static com.sf.jobs.spring.context.jobs.SimpleTestJobType.simpleJsonRecordJobType;
import static com.sf.util.domain.EnumUtil.enumParameters;

/**
 * Created by adityasofat on 24/02/2016.
 *
 * Job Naming standard
 * All job bean creation methods should be names
 * according to
 *
 * GGGName
 *
 * Where the first three letters is the group name
 * and the rest of is the name of the job
 */
@Configuration
public class JobsConfig {

    @Bean
    @Scope("prototype")
    public JobCollectorDefinition<String,JsonRecord> SNSApplicationInstance() {
        return simpleJsonRecordJobType(new IdKey("id"),
                connectableObservable("2_ApplicationInstance.json"),
                enumParameters(DefaultJobExecutionParameters.class));
    }

    @Bean
    @Scope("prototype")
    public JobCollectorDefinition<String,JsonRecord> A2PApplicationInstance() {
        return simpleJsonRecordJobType(new IdKey("id"),
                connectableObservable("2_ApplicationInstance.json"),
                enumParameters(DefaultJobExecutionParameters.class));
    }

}