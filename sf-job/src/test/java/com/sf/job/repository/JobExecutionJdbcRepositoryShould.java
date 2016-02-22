package com.sf.job.repository;

import com.sf.job.domain.IdKey;
import com.sf.job.domain.Job;
import com.sf.job.domain.JobExecution;
import com.sf.job.domain.JobName;
import com.sf.job.repository.jdbc.JobExecutionJdbcRepository;
import com.sf.job.repository.jdbc.JobJdbcRepository;
import com.sf.job.testjob.DefaultJobExecutionParameters;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static com.sf.util.datasource.DataSourceUtil.simpleDatSource;
import static com.sf.job.domain.JobExecutionParametersUtil.jobExecutionParametersMap;

/**
 * Created by adityasofat on 14/02/2016.
 */
public class JobExecutionJdbcRepositoryShould {

    @Test
    public void createJobExecutionRecord(){
        //Given
        JobExecutionJdbcRepository jobExecutionJdbcRepository = new JobExecutionJdbcRepository(simpleDatSource());
        jobExecutionJdbcRepository.init();
        JobExecution jobExecution = jobExecutionJdbcRepository.create(createJob().getJobId(), jobExecutionParametersMap(DefaultJobExecutionParameters.values()));
        MatcherAssert.assertThat(jobExecution, CoreMatchers.equalTo(jobExecution));
    }

    private Job createJob() {
        IdKey dataKey = new IdKey("id");
        JobJdbcRepository jobJdbcRepository = new JobJdbcRepository(simpleDatSource());
        jobJdbcRepository.init();
        return jobJdbcRepository.create(JobName.SNS_ApplicationInstance, dataKey);
    }


}