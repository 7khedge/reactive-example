package com.sf.job;

import com.sf.job.domain.IdKey;
import com.sf.job.domain.JobName;
import rx.observables.ConnectableObservable;

import java.util.Map;

/**
 * Created by adityasofat on 11/11/2015.
 */
public class JobDefinition<F,T> {
    private final JobName jobName;
    private final IdKey idKey;
    private final ConnectableObservable<F> items;
    private final ItemReader<F,T> itemReader;
    private final ItemProcessor<T> itemProcessor;
    private final ItemWriter<T> itemWriter;
    private final Map<String,Integer> jobExecutionParameters;

    public JobDefinition(JobName jobName,
                         IdKey idKey,
                         ConnectableObservable<F> items,
                         ItemReader<F,T> itemReader,
                         ItemProcessor<T> itemProcessor,
                         ItemWriter<T> itemWriter,
                         Map<String,Integer> jobExecutionParameters) {
        this.jobName = jobName;
        this.idKey = idKey;
        this.items = items;
        this.itemReader = itemReader;
        this.itemProcessor = itemProcessor;
        this.itemWriter = itemWriter;
        this.jobExecutionParameters = jobExecutionParameters;
    }

    public JobName getJobName() {
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

    public Map<String,Integer> getJobExecutionParameters() {
        return jobExecutionParameters;
    }
}
