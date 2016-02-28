package com.sf.jobs.spring.context.jobs;

import com.sf.job.definition.JobCollectorDefinition;
import com.sf.job.definition.JobCollectorDefinitionBuilder;
import com.sf.job.domain.IdKey;
import com.sf.job.domain.JobType;
import com.sf.job.domain.JsonRecord;
import com.sf.job.item.impl.JsonRecordItemCollectorProcessor;
import com.sf.job.item.impl.JsonRecordItemReader;
import com.sf.job.item.impl.JsonRecordItemWriter;
import com.sf.job.parameter.DefaultJobExecutionParameters;
import org.springframework.context.annotation.Configuration;
import rx.observables.ConnectableObservable;

import java.util.Map;

import static com.sf.jobs.spring.context.jobs.ItemObservable.connectableObservable;
import static com.sf.jobs.spring.context.jobs.ItemProcessorConfig.jsonRecordItemCollectorProcessor;
import static com.sf.jobs.spring.context.jobs.ItemReaderConfig.jsonRecordItemReader;
import static com.sf.jobs.spring.context.jobs.ItemWriteConfig.jsonRecordItemWriter;

/**
 * Create a JobDefinition for SimpleTestJob Type
 */
public class SimpleTestJobType {

    public static JobCollectorDefinition<String, JsonRecord> simpleJobType(JobType jobType,
                                                                    IdKey idKey,
                                                                    ConnectableObservable<String> connectableObservable,
                                                                    Map<String, Integer> parameterMap) {
        /**
         * returns definition based on JobType
         */
        return JobCollectorDefinitionBuilder.<String, JsonRecord>jobDefinition()
                .jobType(jobType)
                .idKey(idKey)
                .observableItems(connectableObservable)
                .itemReader(jsonRecordItemReader(idKey))
                .itemProcessor(jsonRecordItemCollectorProcessor())
                .itemWriter(jsonRecordItemWriter())
                .jobExecutionParameters(parameterMap)
                .build();
    }


}
