package com.sf.job.service;

import com.sf.job.domain.IdKey;
import com.sf.job.domain.JobType;
import com.sf.job.domain.JsonRecord;
import com.sf.job.item.*;
import com.sf.job.item.impl.JsonRecordItemCollectorProcessor;
import com.sf.job.item.impl.JsonRecordItemReader;
import com.sf.job.item.impl.JsonRecordItemWriter;
import com.sf.job.observer.FileObserver;
import com.sf.job.parameter.DefaultJobExecutionParameters;
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

    public TestJobConfig() {

    }

    @Override
    public JobType getName() {
        return JobType.simpleJsonRecord;
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
        return new JsonRecordItemCollectorProcessor();
    }

    @Override
    public ItemWriter<JsonRecord> getItemWrite() {
        return new JsonRecordItemWriter();
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
