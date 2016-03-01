package com.sf.job.repository;

import com.sf.job.domain.*;
import com.sf.job.repository.jdbc.JobExecutionJdbcRepository;
import com.sf.job.repository.jdbc.JobJdbcRepository;
import com.sf.job.parameter.DefaultJobExecutionParameters;
import com.sf.util.datasource.DataSourceUtil;
import com.sf.util.datasource.TruncateUtil;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static com.sf.util.datasource.DataSourceUtil.simpleDatSource;
import static com.sf.job.domain.JobExecutionParametersUtil.jobExecutionParametersMap;

/**
 * Created by adityasofat on 14/02/2016.
 */
public class JobExecutionJdbcRepositoryShould {

    private JobExecutionJdbcRepository jobExecutionJdbcRepository = new JobExecutionJdbcRepository(simpleDatSource());

    @Before
    public void setup(){
        TruncateUtil truncateUtil = new TruncateUtil(DataSourceUtil.simpleDatSource());
        truncateUtil.truncateTables(JobExecutionJdbcRepository.tableName,JobJdbcRepository.tableName);
        jobExecutionJdbcRepository.init();
    }

    @Test
    public void createJobExecutionRecord(){
        //Given
        JobExecution jobExecution = jobExecutionJdbcRepository.create(createJob().getJobId(), jobExecutionParametersMap(DefaultJobExecutionParameters.values()));
        MatcherAssert.assertThat(jobExecution.getStatus(), CoreMatchers.equalTo(JobExecutionStatus.RUNNING));
    }

    private Job createJob() {
        JobJdbcRepository jobJdbcRepository = new JobJdbcRepository(simpleDatSource());
        jobJdbcRepository.init();
        return jobJdbcRepository.create(new JobName("SNS","ApplicationInstance"), new IdKey("id"));
    }

}