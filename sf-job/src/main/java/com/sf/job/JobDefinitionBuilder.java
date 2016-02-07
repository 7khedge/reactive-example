package com.sf.job;

/**
 * Created by adityasofat on 11/11/2015.
 */
public class JobDefinitionBuilder<F,T> {

    private String jobName;
    private ItemReader<F> itemReader;
    private ItemProcessor<F, T> itemProcessor;
    private ItemWriter<T> itemWriter;

    private JobDefinitionBuilder() {
    }

    public static <F,T> JobDefinitionBuilder<F,T> jobDefinition() {
        return new JobDefinitionBuilder<F,T>();
    }

    public JobDefinitionBuilder name(String jobName) {
        this.jobName = jobName;
        return this;
    }

    public JobDefinition<F,T> build() {
        return new JobDefinition<F,T>(jobName,itemReader, itemProcessor, itemWriter);
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
}
