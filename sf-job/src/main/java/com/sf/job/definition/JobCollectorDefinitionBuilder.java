package com.sf.job.definition;

import com.sf.job.domain.IdKey;
import com.sf.job.domain.JobType;
import com.sf.job.item.*;
import rx.observables.ConnectableObservable;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by adityasofat on 11/11/2015.
 */
public class JobCollectorDefinitionBuilder<F,T> {

    private JobType jobType;
    private IdKey idKey;
    private ConnectableObservable<F> observableItems;
    private ItemReader<F,T> itemReader;
    private ItemCollectorProcessor<T> itemProcessor;
    private ItemCollectorWriter<T,F> itemWriter;
    private Map<String,Integer> jobExecutionParameters = new TreeMap<>();

    private JobCollectorDefinitionBuilder() {
    }

    public static <F,T> JobCollectorDefinitionBuilder<F,T> jobDefinition() {
        return new JobCollectorDefinitionBuilder<>();
    }

    public JobCollectorDefinitionBuilder<F,T> jobType(JobType jobType) {
        this.jobType = jobType;
        return this;
    }

    public JobCollectorDefinitionBuilder<F,T> idKey(IdKey idKey){
        this.idKey = idKey;
        return this;
    }

    public JobCollectorDefinition<F,T> build() {
        return new JobCollectorDefinition<>(jobType, idKey, observableItems, itemReader, itemProcessor, itemWriter, jobExecutionParameters);
    }

    public JobCollectorDefinitionBuilder<F,T> itemReader(ItemReader<F,T> itemReader) {
        this.itemReader = itemReader;
        return this;
    }

    public JobCollectorDefinitionBuilder<F,T> itemProcessor(ItemCollectorProcessor<T> itemProcessor) {
        this.itemProcessor = itemProcessor;
        return this;
    }

    public JobCollectorDefinitionBuilder<F,T> itemWriter(ItemCollectorWriter<T,F> itemWriter) {
        this.itemWriter = itemWriter;
        return this;
    }

    public JobCollectorDefinitionBuilder<F,T> jobExecutionParameters(Map<String,Integer> parameterMap) {
        jobExecutionParameters.putAll(parameterMap);
        return this;
    }

    public JobCollectorDefinitionBuilder<F,T> observableItems(ConnectableObservable<F> observableItems) {
        this.observableItems = observableItems;
        return this;
    }

}
