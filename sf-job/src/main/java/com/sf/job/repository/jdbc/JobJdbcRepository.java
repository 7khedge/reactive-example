package com.sf.job.repository.jdbc;


import com.sf.job.domain.IdKey;
import com.sf.job.domain.Job;
import com.sf.job.domain.JobName;
import com.sf.job.repository.JobRepository;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by adityasofat on 13/02/2016.
 */
public class JobJdbcRepository implements JobRepository {

    private SimpleJdbcInsert insertJob;
    private JdbcTemplate jdbcTemplate;
    private final static String tableName = "job";
    private final static String jobIdColumn = "jobId";
    private final static String jobNameColumn = "jobName";
    private final static String dataKeyColumn = "dataKey";

    public JobJdbcRepository(DataSource dataSource) {
        this.insertJob = new SimpleJdbcInsert(dataSource)
                .withTableName(tableName)
                .usingGeneratedKeyColumns(jobIdColumn);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void init(){
        this.insertJob.compile();
    }

    @Override
    public Job create(JobName jobName, IdKey dataKey) {
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put(jobNameColumn, jobName);
        parameters.put(dataKeyColumn, dataKey);
        Number newId = insertJob.executeAndReturnKey(parameters);
        return new Job(newId.longValue(), jobName, dataKey);
    }

    @Override
    public Job read(JobName jobName) {
        SQL sql = new SQL().SELECT("*").FROM(tableName).WHERE(jobNameColumn + "='" + jobName + "'");
        return jdbcTemplate.queryForObject(sql.toString(), (rs, rowNum) -> {
            return new Job(rs.getLong(jobIdColumn),new JobName(rs.getString(jobNameColumn)), new IdKey(rs.getString(dataKeyColumn)));
        });
    }

}
