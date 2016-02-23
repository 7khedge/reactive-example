package com.sf.job.repository;

import com.sf.job.domain.IdKey;
import com.sf.job.domain.Job;
import com.sf.job.domain.JobName;
import com.sf.job.repository.jdbc.JobExecutionJdbcRepository;
import com.sf.job.repository.jdbc.JobJdbcRepository;
import com.sf.util.datasource.DataSourceUtil;
import com.sf.util.datasource.TruncateUtil;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by adityasofat on 14/02/2016.
 */
public class JobJdbcRepositoryShould {

    private JobJdbcRepository jobJdbcRepository = new JobJdbcRepository(DataSourceUtil.simpleDatSource());

    @Before
    public void setup(){
        TruncateUtil truncateUtil = new TruncateUtil(DataSourceUtil.simpleDatSource());
        truncateUtil.truncateTables(JobExecutionJdbcRepository.tableName,JobJdbcRepository.tableName);
        jobJdbcRepository.init();
    }

    @Test
    public void createAJob(){
        Job job = jobJdbcRepository.create(JobName.SNS_ApplicationInstance, new IdKey("id"));
        MatcherAssert.assertThat(job.getJobName(), CoreMatchers.equalTo(JobName.SNS_ApplicationInstance));
    }

    @Test
    public void readAJob(){
        Job createdJob = jobJdbcRepository.create(JobName.SNS_ApplicationInstance, new IdKey("id"));
        Job readJob = jobJdbcRepository.read(JobName.SNS_ApplicationInstance);
        MatcherAssert.assertThat(createdJob,CoreMatchers.equalTo(readJob));
    }

}