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
import com.sf.util.file.FileUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import rx.observables.ConnectableObservable;

/**
 * Created by adityasofat on 24/02/2016.
 */
@Configuration
public class JobsConfig {

    @Bean
    @Scope( scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public JobCollectorDefinition<String,JsonRecord> simpleJsonRecordJob() {
        return getTestJob(JobType.simpleJsonRecord,
                new IdKey("id"),
                connectableObservable("2_ApplicationInstance.json"));
    }

    private ConnectableObservable<String> connectableObservable(String fileName){
        return new FileObserver().createObserver(FileUtil.getClassPathInputStream(fileName));
    }

    private JobCollectorDefinition<String, JsonRecord> getTestJob(JobType jobType,
                                                                  IdKey idKey,
                                                                  ConnectableObservable<String> connectableObservable) {
        return JobCollectorDefinitionBuilder.<String, JsonRecord>jobDefinition()
                .jobType(jobType)
                .idKey(idKey)
                .observableItems(connectableObservable)
                .itemReader(new JsonRecordItemReader(idKey))
                .itemProcessor(new JsonRecordItemCollectorProcessor())
                .itemWriter(new JsonRecordItemWriter())
                .jobExecutionParameters(DefaultJobExecutionParameters.class)
                .build();
    }
}