package com.sf.jobx.config;


import com.sf.jobx.domain.IdKey;
import com.sf.jobx.item.ItemProcessor;
import com.sf.jobx.item.ItemReader;
import com.sf.jobx.item.ItemWriter;
import rx.observables.ConnectableObservable;

import java.util.Map;

/**
 * Created by adityasofat on 20/02/2016.
 */
public interface JobConfig<F,T> {

    IdKey getId();

    ConnectableObservable<F> getItems();

    ItemReader<F,T> getItemReader();

    ItemProcessor<T> getItemProcessor();

    ItemWriter<T> getItemWriter();

    Map<String,Integer> getParameters();
}
