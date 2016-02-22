package com.sf.job.service;

import com.sf.job.*;
import com.sf.job.domain.IdKey;
import com.sf.job.domain.JobName;
import com.sf.job.domain.JsonRecord;
import com.sf.job.testjob.*;
import com.sf.util.file.FileUtil;
import rx.observables.ConnectableObservable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by adityasofat on 20/02/2016.
 */
public class TestJobConfig implements JobConfig<String,JsonRecord> {

    private final List<JsonRecord> processedCollector;
    private final List<String> writtenCollector;

    public TestJobConfig() {
        this.processedCollector = new ArrayList<>();
        this.writtenCollector = new ArrayList<>();
    }

    public List<JsonRecord> getProcessedCollector() {
        return new ArrayList<>(processedCollector);
    }

    public List<String> getWrittenCollector() {
        return new ArrayList<>(writtenCollector);
    }

    @Override
    public JobName getName() {
        return JobName.SNS_ApplicationInstance;
    }

    @Override
    public IdKey getId() {
        return new IdKey("Id");
    }

    @Override
    public ConnectableObservable<String> getObservableItems() {
        return new FileObserver().createObserver(FileUtil.getClassPathInputStream("2_ApplicationInstance.json"));
    }

    @Override
    public ItemReader<String,JsonRecord> getItemReader() {
        return new JsonRecordItemReader(getId());
    }

    @Override
    public ItemProcessor<JsonRecord> getItemProcessor() {
        return new JsonRecordItemProcessor(processedCollector);
    }

    @Override
    public ItemWriter<JsonRecord> getItemWrite() {
        return new JsonRecordItemWriter(writtenCollector);
    }

    @Override
    public Map<String,Integer> getParameters() {
        Map<String,Integer> parameterMap = new TreeMap<>();
         for(DefaultJobExecutionParameters defaultJobExecutionParameter : DefaultJobExecutionParameters.values()){
             parameterMap.put(defaultJobExecutionParameter.toString(),0);
        }
        return parameterMap;
    }
}
