package com.sf.job;

import rx.observables.ConnectableObservable;

import java.util.EnumMap;

/**
 * Created by adityasofat on 11/11/2015.
 */
public class JobDefinition<F,T,E extends Enum<E>> {
    private final String jobName;
    private final IdKey idKey;
    private final ConnectableObservable<F> items;
    private final ItemReader<F,T> itemReader;
    private final ItemProcessor<T> itemProcessor;
    private final ItemWriter<T> itemWriter;
    private final EnumMap<E,Integer> jobExecutionParameters;

    public JobDefinition(String jobName,
                         IdKey idKey,
                         ConnectableObservable<F> items,
                         ItemReader<F,T> itemReader,
                         ItemProcessor<T> itemProcessor,
                         ItemWriter<T> itemWriter,
                         EnumMap<E,Integer> jobExecutionParameters) {
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

    public EnumMap<E,Integer> getJobExecutionParameters() {
        return jobExecutionParameters;
    }
}
