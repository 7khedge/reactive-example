package com.sf.jobs.spring.context;

import com.sf.job.definition.JobCollectorDefinition;
import com.sf.job.definition.JobCollectorDefinitionBuilder;
import com.sf.job.item.impl.JsonRecordItemCollectorProcessor;
import com.sf.job.item.impl.JsonRecordItemReader;
import com.sf.job.item.impl.JsonRecordItemWriter;
import com.sf.job.observer.FileObserver;
import com.sf.job.definition.JobDefinition;
import com.sf.job.definition.JobDefinitionBuilder;
import com.sf.job.domain.IdKey;
import com.sf.job.domain.JobType;
import com.sf.job.domain.JsonRecord;
import com.sf.job.parameter.DefaultJobExecutionParameters;
import com.sf.jobs.spring.context.jobs.ItemObservable;
import com.sf.jobs.spring.context.jobs.SimpleTestJobType;
import com.sf.util.domain.EnumUtil;
import com.sf.util.file.FileUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import rx.observables.ConnectableObservable;

import static com.sf.jobs.spring.context.jobs.ItemObservable.connectableObservable;
import static com.sf.jobs.spring.context.jobs.SimpleTestJobType.simpleJobType;
import static com.sf.util.domain.EnumUtil.enumParameters;

/**
 * Created by adityasofat on 24/02/2016.
 */
@Configuration
public class JobsConfig {

    @Bean
    @Scope("prototype")
    public JobCollectorDefinition<String,JsonRecord> simpleJsonRecordJob() {
        return simpleJobType(JobType.simpleJsonRecord,
                new IdKey("id"),
                connectableObservable("2_ApplicationInstance.json"),
                enumParameters(DefaultJobExecutionParameters.class));
    }




}