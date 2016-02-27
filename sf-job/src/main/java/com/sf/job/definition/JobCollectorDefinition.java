package com.sf.job.definition;

import com.sf.job.domain.IdKey;
import com.sf.job.domain.JobType;
import com.sf.job.item.*;
import rx.observables.ConnectableObservable;

import java.util.Map;

/**
 * Created by adityasofat on 27/02/2016.
 */
public class JobCollectorDefinition<F,T>  {

    private final JobType jobType;
    private final IdKey idKey;
    private final ConnectableObservable<F> items;
    private final ItemReader<F,T> itemReader;
    private final ItemCollectorProcessor<T> itemProcessor;
    private final ItemCollectorWriter<T,F> itemWriter;
    private final Map<String,Integer> jobExecutionParameters;

    public JobCollectorDefinition(JobType jobType,
                                  IdKey idKey,
                                  ConnectableObservable<F> items,
                                  ItemReader<F,T> itemReader,
                                  ItemCollectorProcessor<T> itemProcessor,
                                  ItemCollectorWriter<T,F> itemWriter,
                                  Map<String,Integer> jobExecutionParameters) {
        this.jobType = jobType;
        this.idKey = idKey;
        this.items = items;
        this.itemReader = itemReader;
        this.itemProcessor = itemProcessor;
        this.itemWriter = itemWriter;
        this.jobExecutionParameters = jobExecutionParameters;
    }

    public JobType getJobType() {
        return jobType;
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

    public ItemCollectorProcessor<T> getItemCollectorProcessor() {
        return itemProcessor;
    }

    public ItemCollectorWriter<T,F> getItemCollectorWriter() {
        return itemWriter;
    }

    public Map<String,Integer> getJobExecutionParameters() {
        return jobExecutionParameters;
    }
}
