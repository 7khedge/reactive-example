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
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

/**
 * Created by adityasofat on 14/02/2016.
 */
public class JobJdbcRepositoryShould {

    private JobJdbcRepository jobJdbcRepository = new JobJdbcRepository(DataSourceUtil.simpleDatSource());
    private JobName testJob1 = new JobName("SNS","ApplicationInstance");
    private IdKey idKey = new IdKey("id");


    @Before
    public void setup(){
        TruncateUtil truncateUtil = new TruncateUtil(DataSourceUtil.simpleDatSource());
        truncateUtil.truncateTables(JobExecutionJdbcRepository.tableName,JobJdbcRepository.tableName);
        jobJdbcRepository.init();
    }

    @Test
    public void createAJob(){
        Job job = jobJdbcRepository.create(testJob1, idKey);
        MatcherAssert.assertThat(job.getJobName(), CoreMatchers.equalTo(testJob1));
    }

    @Test
    public void readAJob(){
        Job createdJob = jobJdbcRepository.create(testJob1, idKey);
        Job readJob = jobJdbcRepository.read(testJob1);
        MatcherAssert.assertThat(createdJob,CoreMatchers.equalTo(readJob));
    }

    @Test
    public void readAllJobs(){
        JobName testJob2 = new JobName("A2P","ApplicationInstance");
        jobJdbcRepository.create(testJob1, idKey);
        jobJdbcRepository.create(testJob2, idKey);
        MatcherAssert.assertThat(jobJdbcRepository.readAllJobs().stream().map(Job::getJobName).collect(Collectors.toList()), Matchers.contains(testJob1,testJob2));
    }


}