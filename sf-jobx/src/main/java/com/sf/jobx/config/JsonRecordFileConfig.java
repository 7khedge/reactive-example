package com.sf.jobx.config;

import com.sf.jobx.domain.IdKey;
import com.sf.jobx.domain.JsonRecord;
import com.sf.jobx.adaptor.ItemAdaptor;
import com.sf.jobx.item.ItemProcessor;
import com.sf.jobx.item.ItemReader;
import com.sf.jobx.item.ItemWriter;
import com.sf.jobx.item.impl.JsonRecordItemProcessor;
import com.sf.jobx.item.impl.JsonRecordItemReader;
import com.sf.jobx.item.impl.JsonRecordItemWriter;
import com.sf.jobx.observer.FileObserver;
import com.sf.jobx.parameter.DefaultJobExecutionParameters;
import com.sf.util.domain.EnumUtil;
import rx.observables.ConnectableObservable;

import java.io.InputStream;
import java.util.Map;

/**
 * Created by adityasofat on 08/03/2016.
 */
public class JsonRecordFileConfig implements JobConfig<String,JsonRecord> {

    private final IdKey idKey;
    private final InputStream jsonRecordInputStream;
    private final ItemAdaptor<JsonRecord> itemAdaptor;

    public JsonRecordFileConfig(IdKey idKey, InputStream jsonRecordInputStream, ItemAdaptor<JsonRecord>  itemAdaptor) {
        this.idKey = idKey;
        this.jsonRecordInputStream = jsonRecordInputStream;
        this.itemAdaptor = itemAdaptor;
    }

    @Override
    public IdKey getId() {
        return idKey;
    }

    @Override
    public ConnectableObservable<String> getItems() {
        return new FileObserver().createObserver(jsonRecordInputStream);
    }

    @Override
    public ItemReader<String,JsonRecord> getItemReader() {
        return new JsonRecordItemReader(getId());
    }

    @Override
    public ItemProcessor<JsonRecord> getItemProcessor() {
        return new JsonRecordItemProcessor(itemAdaptor);
    }

    @Override
    public ItemWriter<JsonRecord> getItemWriter() {
        return new JsonRecordItemWriter(itemAdaptor);
    }

    @Override
    public Map<String, Integer> getParameters() {
        return EnumUtil.enumParameters(DefaultJobExecutionParameters.class);
    }


}
