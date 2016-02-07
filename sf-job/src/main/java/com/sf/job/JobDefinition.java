package com.sf.job;

import java.util.EnumMap;

/**
 * Created by adityasofat on 11/11/2015.
 */
public class JobDefinition<F,T,E extends Enum<E>> {
    private final String jobName;
    private final ItemReader<F> itemReader;
    private final ItemProcessor<F,T> itemProcessor;
    private final ItemWriter<T> itemWriter;
    private final EnumMap<E,Integer> jobExecutionParameters;

    public JobDefinition(String jobName,
                         ItemReader<F> itemReader,
                         ItemProcessor<F, T> itemProcessor,
                         ItemWriter<T> itemWriter,
                         EnumMap<E,Integer> jobExecutionParameters) {
        this.jobName = jobName;
        this.itemReader = itemReader;
        this.itemProcessor = itemProcessor;
        this.itemWriter = itemWriter;
        this.jobExecutionParameters = jobExecutionParameters;
    }

    public String getJobName() {
        return jobName;
    }

    public ItemReader<F> getItemReader() {
        return itemReader;
    }

    public ItemProcessor<F, T> getItemProcessor() {
        return itemProcessor;
    }

    public ItemWriter<T> getItemWriter() {
        return itemWriter;
    }

    public EnumMap<E,Integer> getJobExecutionParameters() {
        return jobExecutionParameters;
    }
}
