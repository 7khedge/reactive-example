package com.sf.job;

/**
 * Created by adityasofat on 11/11/2015.
 */
public class JobDefinition<F,T> {
    private final String jobName;
    private final ItemReader<F> itemReader;
    private final ItemProcessor<F,T> itemProcessor;
    private final ItemWriter<T> itemWriter;

    public JobDefinition(String jobName, ItemReader<F> itemReader, ItemProcessor<F,T> itemProcessor, ItemWriter<T> itemWriter) {
        this.jobName = jobName;
        this.itemReader = itemReader;
        this.itemProcessor = itemProcessor;
        this.itemWriter = itemWriter;
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
}
