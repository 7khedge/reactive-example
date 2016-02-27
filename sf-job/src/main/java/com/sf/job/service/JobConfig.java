package com.sf.job.service;

import com.sf.job.domain.IdKey;
import com.sf.job.item.ItemProcessor;
import com.sf.job.item.ItemReader;
import com.sf.job.item.ItemWriter;
import com.sf.job.domain.JobType;
import rx.observables.ConnectableObservable;

import java.util.Map;

/**
 * Created by adityasofat on 20/02/2016.
 */
public interface JobConfig<F,T> {
    JobType getName();

    IdKey getId();

    ConnectableObservable<F> getObservableItems();

    ItemReader<F,T> getItemReader();

    ItemProcessor<T> getItemProcessor();

    ItemWriter<T> getItemWrite();

    Map<String,Integer> getParameters();
}
