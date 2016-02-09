package com.sf.job;

import java.util.EnumMap;

/**
 * Created by adityasofat on 11/11/2015.
 */
public class JobDefinitionBuilder<F,T,E extends Enum<E>> {

    private String jobName;
    private IdKey idKey;
    private ItemReader<F,T> itemReader;
    private ItemProcessor<T> itemProcessor;
    private ItemWriter<T> itemWriter;
    private EnumMap<E,Integer> jobExecutionParameters;

    private JobDefinitionBuilder() {
    }

    public static <F,T,E extends Enum<E>> JobDefinitionBuilder<F,T,E> jobDefinition() {
        return new JobDefinitionBuilder<F,T,E>();
    }

    public JobDefinitionBuilder<F,T,E> name(String jobName) {
        this.jobName = jobName;
        return this;
    }

    public JobDefinitionBuilder<F,T,E> idKey(IdKey idKey){
        this.idKey = idKey;
        return this;
    }

    public JobDefinition<F,T,E> build() {
        return new JobDefinition<F,T,E>(jobName, idKey, itemReader,  itemProcessor, itemWriter, jobExecutionParameters);
    }

    public JobDefinitionBuilder<F,T,E> itemReader(ItemReader<F,T> itemReader) {
        this.itemReader = itemReader;
        return this;
    }

    public JobDefinitionBuilder<F,T,E> itemProcessor(ItemProcessor<T> itemProcessor) {
        this.itemProcessor = itemProcessor;
        return this;
    }

    public JobDefinitionBuilder<F,T,E> itemWriter(ItemWriter<T> itemWriter) {
        this.itemWriter = itemWriter;
        return this;
    }

    public JobDefinitionBuilder<F,T,E> jobExecutionParameters(EnumMap<E,Integer> jobExecutionParameters) {
        this.jobExecutionParameters = jobExecutionParameters;
        return this;
    }
}
