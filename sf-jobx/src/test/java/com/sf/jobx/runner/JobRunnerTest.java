package com.sf.jobx.runner;

import com.sf.jobx.adaptor.impl.ItemCollectingAdaptorImpl;
import com.sf.jobx.domain.JobName;
import com.sf.jobx.domain.JsonRecord;
import com.sf.jobx.item.ItemCollectingAdaptor;
import com.sf.jobx.service.JobFactory;
import com.sf.jobx.service.JobFactoryImpl;
import com.sf.jobx.service.Jobs;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by adityasofat on 21/03/2016.
 */
public class JobRunnerTest {

    private JobFactory<String,JsonRecord> jobFactory = new JobFactoryImpl();
    private JobName jobName = new JobName("SNSApplicationInstance");
    private ItemCollectingAdaptor<JsonRecord> itemAdaptor = new ItemCollectingAdaptorImpl<>();

    @Before
    public void setup(){
        jobFactory.add(jobName, Jobs.SNSApplicationInstance(itemAdaptor));
    }

    @Test
    public void shouldRunJob(){
        SimpleJobRunner<String,JsonRecord> simpleJobRunner = new SimpleJobRunner<>(jobFactory.getName(jobName));
        simpleJobRunner.startJob();
        MatcherAssert.assertThat(itemAdaptor.getCollector().size(), Matchers.not(19));
    }


}