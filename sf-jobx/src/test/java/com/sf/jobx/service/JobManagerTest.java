package com.sf.jobx.service;

import com.sf.jobx.adaptor.impl.ItemCollectingAdaptorImpl;
import com.sf.jobx.domain.JobName;
import com.sf.jobx.domain.JsonRecord;
import com.sf.jobx.item.ItemCollectingAdaptor;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by adityasofat on 15/04/16.
 */
public class JobManagerTest {

    private JobManager jobManager = new JobManagerImpl();
    private JobFactory<String,JsonRecord> jobFactory;
    private ItemCollectingAdaptor<JsonRecord> itemAdaptor = new ItemCollectingAdaptorImpl<>();
    JobName jobName = new JobName("SNSApplicationInstance");

    @Before
    public void setup(){
        jobFactory = JobFactoryTestHelper.factoryWithSingleJob(jobName , itemAdaptor);
    }

    @Test
    public void shouldStartJob(){
        jobManager.startJob(jobFactory.getName(jobName));
    }

}