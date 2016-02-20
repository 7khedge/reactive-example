package com.sf.job;

import rx.observables.ConnectableObservable;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by adityasofat on 11/11/2015.
 */
public class JobDefinitionBuilder<F,T> {

    private String jobName;
    private IdKey idKey;
    private ConnectableObservable<F> observableItems;
    private ItemReader<F,T> itemReader;
    private ItemProcessor<T> itemProcessor;
    private ItemWriter<T> itemWriter;
    private Map<String,Object> jobExecutionParameters = new TreeMap<>();

    private JobDefinitionBuilder() {
    }

    public static <F,T> JobDefinitionBuilder<F,T> jobDefinition() {
        return new JobDefinitionBuilder<>();
    }

    public JobDefinitionBuilder<F,T> name(String jobName) {
        this.jobName = jobName;
        return this;
    }

    public JobDefinitionBuilder<F,T> idKey(IdKey idKey){
        this.idKey = idKey;
        return this;
    }

    public JobDefinition<F,T> build() {
        return new JobDefinition<>(jobName, idKey, observableItems, itemReader, itemProcessor, itemWriter, jobExecutionParameters);
    }

    public JobDefinitionBuilder<F,T> itemReader(ItemReader<F,T> itemReader) {
        this.itemReader = itemReader;
        return this;
    }

    public JobDefinitionBuilder<F,T> itemProcessor(ItemProcessor<T> itemProcessor) {
        this.itemProcessor = itemProcessor;
        return this;
    }

    public JobDefinitionBuilder<F,T> itemWriter(ItemWriter<T> itemWriter) {
        this.itemWriter = itemWriter;
        return this;
    }

    public <E> JobDefinitionBuilder<F,T> jobExecutionParameters(Class<E> enumType) {
        for(E enumInstance : enumType.getEnumConstants()){
            jobExecutionParameters.put(enumInstance.toString(),0);
        }
        return this;
    }

    public JobDefinitionBuilder<F,T> ObservableItems(ConnectableObservable<F> observableItems) {
        this.observableItems = observableItems;
        return this;
    }

}
