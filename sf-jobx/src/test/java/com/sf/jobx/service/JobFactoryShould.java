package com.sf.jobx.service;

import com.sf.jobx.adaptor.ItemAdaptor;
import com.sf.jobx.adaptor.impl.ItemCollectingAdaptorImpl;
import com.sf.jobx.config.JsonRecordFileConfig;
import com.sf.jobx.domain.IdKey;
import com.sf.jobx.domain.JobName;
import com.sf.jobx.domain.JsonRecord;
import com.sf.util.file.FileUtil;

/**
 * Created by adityasofat on 07/03/2016.
 */
public class JobFactoryShould {

    private JobFactory jobFactory = new JobFactoryImpl();

    public void addJobToFactory(){
        ItemAdaptor itemAdaptor = new ItemCollectingAdaptorImpl();
        jobFactory.add(new JobName("SNSApplicationInstance"), SNSApplicationInstance(itemAdaptor));


    }

    private JsonRecordFileConfig SNSApplicationInstance(ItemAdaptor<JsonRecord> jsonRecordItemAdaptor){
        return new JsonRecordFileConfig(new IdKey("id"), FileUtil.getClassPathInputStream("2_ApplicationInstance.json"), jsonRecordItemAdaptor);
    }



}