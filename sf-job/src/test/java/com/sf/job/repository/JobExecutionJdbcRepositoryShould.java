package com.sf.job.repository;

import com.sf.job.domain.Job;
import com.sf.job.domain.JobExecution;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static com.sf.datasource.DataSourceUtil.simpleDatSource;

/**
 * Created by adityasofat on 14/02/2016.
 */
public class JobExecutionJdbcRepositoryShould {

    @Test
    public void createJobExecutionRecord(){
        //Given
        JobExecutionJdbcRepository jobExecutionJdbcRepository = new JobExecutionJdbcRepository(simpleDatSource());
        jobExecutionJdbcRepository.init();
        JobExecution jobExecution = jobExecutionJdbcRepository.create(createJob().getJobId(),);
        MatcherAssert.assertThat(jobExecution, CoreMatchers.equalTo(jobExecution));
    }

    private Job createJob() {
        String jobName = "ApplicationInstance";
        String dataKey = "id";
        JobJdbcRepository jobJdbcRepository = new JobJdbcRepository(simpleDatSource());
        jobJdbcRepository.init();
        return jobJdbcRepository.create(jobName, dataKey);
    }


}