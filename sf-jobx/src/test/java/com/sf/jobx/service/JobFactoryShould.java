package com.sf.jobx.service;

import com.sf.jobx.adaptor.ItemAdaptor;
import com.sf.jobx.adaptor.impl.ItemCollectingAdaptorImpl;
import com.sf.jobx.config.JsonRecordFileConfig;
import com.sf.jobx.domain.IdKey;
import com.sf.jobx.domain.JobName;
import com.sf.jobx.domain.JsonRecord;
import com.sf.util.file.FileUtil;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Created by adityasofat on 07/03/2016.
 */
public class JobFactoryShould {

    private JobFactory jobFactory = new JobFactoryImpl();

    @Test
    public void addJobToFactory(){
        //Given
        ItemAdaptor<JsonRecord> itemAdaptor = new ItemCollectingAdaptorImpl<>();
        JsonRecordFileConfig jsonRecordFileConfig = Jobs.SNSApplicationInstance(itemAdaptor);
        JobName jobName = new JobName("SNSApplicationInstance");
        //When
        jobFactory.add(jobName, jsonRecordFileConfig);
        //Then
        MatcherAssert.assertThat(jobFactory.getName(jobName), CoreMatchers.equalTo(jsonRecordFileConfig));
    }

}