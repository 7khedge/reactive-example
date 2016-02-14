package com.sf.job.repository;

import com.sf.job.domain.JobExecution;
import com.sf.util.date.DateUtil;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by adityasofat on 14/02/2016.
 */
public class JobExecutionJdbcRepository implements  JobExecutionRepository {

    private SimpleJdbcInsert insertJob;

    public JobExecutionJdbcRepository(DataSource dataSource) {
        this.insertJob = new SimpleJdbcInsert(dataSource)
                .withTableName("jobExecution")
                .usingGeneratedKeyColumns("jobExecutionId");
    }

    public void init(){
        this.insertJob.compile();
    }

    @Override
    public JobExecution create(Long jobId, Map<String, String> properties, LocalDateTime startDateTime) {
        LocalDateTime now = LocalDateTime.now();
        Map<String, Object> parameters = new HashMap<>(3);
        parameters.put("jobId", jobId);
        parameters.put("properties", properties);
        parameters.put("startDateTime", DateUtil.asDate(startDateTime));
        Number newId = insertJob.executeAndReturnKey(parameters);
        return new JobExecution(newId.longValue(), jobId, properties, now);
    }

}
