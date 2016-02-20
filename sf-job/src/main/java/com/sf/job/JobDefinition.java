package com.sf.job;

import rx.observables.ConnectableObservable;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by adityasofat on 11/11/2015.
 */
public class JobDefinition<F,T> {
    private final String jobName;
    private final IdKey idKey;
    private final ConnectableObservable<F> items;
    private final ItemReader<F,T> itemReader;
    private final ItemProcessor<T> itemProcessor;
    private final ItemWriter<T> itemWriter;
    private final Map<String,Object> jobExecutionParameters;

    public JobDefinition(String jobName,
                         IdKey idKey,
                         ConnectableObservable<F> items,
                         ItemReader<F,T> itemReader,
                         ItemProcessor<T> itemProcessor,
                         ItemWriter<T> itemWriter,
                         Map<String,Object> jobExecutionParameters) {
        this.jobName = jobName;
        this.idKey = idKey;
        this.items = items;
        this.itemReader = itemReader;
        this.itemProcessor = itemProcessor;
        this.itemWriter = itemWriter;
        this.jobExecutionParameters = jobExecutionParameters;
    }

    public String getJobName() {
        return jobName;
    }

    public IdKey getIdKey() {
        return idKey;
    }

    public ConnectableObservable<F> getItems() {
        return items;
    }

    public ItemReader<F,T> getItemReader() {
        return itemReader;
    }

    public ItemProcessor<T> getItemProcessor() {
        return itemProcessor;
    }

    public ItemWriter<T> getItemWriter() {
        return itemWriter;
    }

    public Map<String,Object> getJobExecutionParameters() {
        return jobExecutionParameters;
    }
}
