package com.sf.job.service;

import com.sf.job.definition.SimpleJobDefinition;
import com.sf.job.definition.SimpleJobDefinitionBuilder;
import com.sf.job.domain.*;
import com.sf.job.repository.jdbc.JobExecutionJdbcRepository;
import com.sf.job.repository.jdbc.JobJdbcRepository;
import com.sf.util.datasource.DataSourceUtil;
import com.sf.util.datasource.TruncateUtil;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by adityasofat on 20/02/2016.
 */
public class JobServiceShould {

    /**
     * Mocks out the following call
     * String[] beanNamesForType = applicationContext.getBeanNamesForType(JobCollectorDefinition.class);
     */
    private final String jobBeans[] = {"SNSApplicationInstance", "A2PApplicationInstance"};

    private JobService jobService = new DefaultJobService(
            new JobFactoryImpl(jobBeans, new JobJdbcRepository(DataSourceUtil.simpleDatSource())),
            new JobExecutionJdbcRepository(DataSourceUtil.simpleDatSource()));
    private SimpleJobDefinition<String, JsonRecord> testJob = getTestJob(new TestJobConfig());

    @Before
    public void clearDownTables(){
        TruncateUtil truncateUtil = new TruncateUtil(DataSourceUtil.simpleDatSource());
        truncateUtil.truncateAllTables();
    }

    private SimpleJobDefinition<String,JsonRecord> getTestJob(JobConfig<String,JsonRecord> jobConfig) {
        return SimpleJobDefinitionBuilder.<String, JsonRecord>jobDefinition()
                .jobType(jobConfig.getName())
                .idKey(jobConfig.getId())
                .observableItems(jobConfig.getObservableItems())
                .itemReader(jobConfig.getItemReader())
                .itemProcessor(jobConfig.getItemProcessor())
                .itemWriter(jobConfig.getItemWrite())
                .jobExecutionParameters(jobConfig.getParameters())
                .build();
    }

}