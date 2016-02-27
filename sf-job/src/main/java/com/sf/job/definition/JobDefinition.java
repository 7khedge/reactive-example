package com.sf.job.definition;

import com.sf.job.domain.IdKey;
import com.sf.job.domain.JobType;
import com.sf.job.item.ItemProcessor;
import com.sf.job.item.ItemReader;
import com.sf.job.item.ItemWriter;
import rx.observables.ConnectableObservable;

import java.util.Map;

/**
 * Created by adityasofat on 11/11/2015.
 */
public class JobDefinition<F,T> {
    private final JobType jobType;
    private final IdKey idKey;
    private final ConnectableObservable<F> items;
    private final ItemReader<F,T> itemReader;
    private final ItemProcessor<T> itemProcessor;
    private final ItemWriter<T> itemWriter;
    private final Map<String,Integer> jobExecutionParameters;

    public JobDefinition(JobType jobType,
                         IdKey idKey,
                         ConnectableObservable<F> items,
                         ItemReader<F,T> itemReader,
                         ItemProcessor<T> itemProcessor,
                         ItemWriter<T> itemWriter,
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

    public ItemProcessor<T> getItemProcessor() {
        return itemProcessor;
    }

    public ItemWriter<T> getItemWriter() {
        return itemWriter;
    }

    public Map<String,Integer> getJobExecutionParameters() {
        return jobExecutionParameters;
    }
}
