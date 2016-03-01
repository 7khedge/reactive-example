package com.sf.jobs.spring.context;

import com.sf.job.domain.Job;
import com.sf.job.domain.JobName;
import com.sf.job.repository.JobRepository;
import com.sf.job.repository.jdbc.JobJdbcRepository;
import com.sf.util.datasource.DataSourceUtil;
import com.sf.util.datasource.TruncateUtil;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by adityasofat on 29/02/2016.
 */
public class JobFactoryShould {

    private JobsConfig jobsConfig = new JobsConfig();
    private JobRepository jobRepository = new JobJdbcRepository(DataSourceUtil.simpleDatSource());

    @Before
    public void setup() {
        TruncateUtil truncateUtil = new TruncateUtil(DataSourceUtil.simpleDatSource());
        truncateUtil.truncateTables(JobJdbcRepository.tableName);
    }

    /**
     * Mock out the following call
     * String[] beanNamesForType = applicationContext.getBeanNamesForType(JobCollectorDefinition.class);
     */
    private final String jobBeans[] = {"SNSApplicationInstance", "A2PApplicationInstance"};

    @Test(expected = RuntimeException.class)
    public void throwsNoJobDefinitionException() {
        //Given
        //No JobDefinitions added
        JobFactory jobFactory = new JobFactoryImpl(jobBeans, jobRepository);
        jobFactory.validateJobDefinitions();
    }

    @Test
    public void doesNotThrowNoJobDefinitionException() {
        //Given
        getPopulatedJobFactory().validateJobDefinitions();
        MatcherAssert.assertThat(true, CoreMatchers.equalTo(true));
    }

    @Test
    public void syncJobNamesWithDatabase(){
        //Given
        getPopulatedJobFactory().validateJobDefinitions();
        List<Job> jobs = jobRepository.readAllJobs();
        MatcherAssert.assertThat(jobs.size(),CoreMatchers.equalTo(2));
    }

    private JobFactory getPopulatedJobFactory(){
        JobFactory jobFactory = new JobFactoryImpl(jobBeans, new JobJdbcRepository(DataSourceUtil.simpleDatSource()));
        jobFactory.add(new JobName(jobBeans[0]), jobsConfig.SNSApplicationInstance());
        jobFactory.add(new JobName(jobBeans[1]), jobsConfig.A2PApplicationInstance());
        return jobFactory;
    }


}