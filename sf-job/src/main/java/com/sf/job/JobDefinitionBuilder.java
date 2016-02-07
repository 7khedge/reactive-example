package com.sf.job;

import java.util.EnumMap;

/**
 * Created by adityasofat on 11/11/2015.
 */
public class JobDefinitionBuilder<F,T,E extends Enum<E>> {

    private String jobName;
    private ItemReader<F> itemReader;
    private ItemProcessor<F, T> itemProcessor;
    private ItemWriter<T> itemWriter;
    private EnumMap<E,Integer> jobExecutionParameters;

    private JobDefinitionBuilder() {
    }

    public static <F,T,E extends Enum<E>> JobDefinitionBuilder<F,T,E> jobDefinition() {
        return new JobDefinitionBuilder<F,T,E>();
    }

    public JobDefinitionBuilder name(String jobName) {
        this.jobName = jobName;
        return this;
    }

    public JobDefinition<F,T,E> build() {
        return new JobDefinition<F,T,E>(jobName,itemReader, itemProcessor, itemWriter, jobExecutionParameters);
    }

    public JobDefinitionBuilder itemReader(ItemReader<F> itemReader) {
        this.itemReader = itemReader;
        return this;
    }

    public JobDefinitionBuilder itemProcessor(ItemProcessor<F,T> itemProcessor) {
        this.itemProcessor = itemProcessor;
        return this;
    }

    public JobDefinitionBuilder itemWriter(ItemWriter<T> itemWriter) {
        this.itemWriter = itemWriter;
        return this;
    }

    public JobDefinitionBuilder jobExecutionParameters(EnumMap<E,Integer> jobExecutionParameters) {
        this.jobExecutionParameters = jobExecutionParameters;
        return this;
    }
}
