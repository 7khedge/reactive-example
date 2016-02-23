package com.sf.job.repository.jdbc;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import com.sf.job.domain.JobExecution;
import com.sf.job.repository.JobExecutionRepository;
import com.sf.util.date.DateUtil;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by adityasofat on 14/02/2016.
 */
public class JobExecutionJdbcRepository implements JobExecutionRepository {

    private SimpleJdbcInsert insertJob;
    private Type stringIntegerMap = new TypeToken<Map<String,Integer>>(){}.getType();
    private Gson gson = new Gson();


    public JobExecutionJdbcRepository(DataSource dataSource) {
        this.insertJob = new SimpleJdbcInsert(dataSource)
                .withTableName("jobExecution")
                .usingGeneratedKeyColumns("jobExecutionId");
    }

    public void init(){
        this.insertJob.compile();
    }

    @Override
    public JobExecution create(Long jobId, Map<String, Integer> properties) {
        LocalDateTime now = LocalDateTime.now();
        Map<String, Object> parameters = new HashMap<>(3);
        parameters.put("jobId", jobId);
        parameters.put("properties", gson.toJson(properties, stringIntegerMap));
        parameters.put("startDateTime", DateUtil.asDate(now));
        Number newId = insertJob.executeAndReturnKey(parameters);
        return new JobExecution(newId.longValue(), jobId, properties, now);
    }

}
