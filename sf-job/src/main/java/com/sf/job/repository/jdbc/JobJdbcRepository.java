package com.sf.job.repository.jdbc;


import com.sf.job.domain.IdKey;
import com.sf.job.domain.Job;
import com.sf.job.domain.JobName;
import com.sf.job.repository.JobRepository;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by adityasofat on 13/02/2016.
 */
public class JobJdbcRepository implements JobRepository {

    private SimpleJdbcInsert insertJob;
    private JdbcTemplate jdbcTemplate;
    public final static String tableName = "job";
    private enum dbColumns {
        jobId,
        jobGroup,
        jobName,
        dataKey
    }

    private RowMapper<Job> mapper =  (rs, rowNum) -> {
        try {
            return new Job(rs.getLong(dbColumns.jobId.name()),
                    new JobName(rs.getString(dbColumns.jobGroup.name()),rs.getString(dbColumns.jobName.name())),
                    new IdKey(rs.getString(dbColumns.dataKey.name())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    public JobJdbcRepository(DataSource dataSource) {
        this.insertJob = new SimpleJdbcInsert(dataSource)
                .withTableName(tableName)
                .usingGeneratedKeyColumns(dbColumns.jobId.name());
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void init(){
        this.insertJob.compile();
    }

    @Override
    public Job create(JobName jobName, IdKey dataKey) {
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put(dbColumns.jobName.name(), jobName.getName());
        parameters.put(dbColumns.jobGroup.name(), jobName.getGroup());
        parameters.put(dbColumns.dataKey.name(), dataKey.value());
        Number newId = insertJob.executeAndReturnKey(parameters);
        return new Job(newId.longValue(), jobName, dataKey);
    }

    @Override
    public Job read(JobName jobName) {
        SQL sql = new SQL().SELECT("*").FROM(tableName)
                .WHERE(dbColumns.jobGroup.name() + "='" + jobName.getGroup() + "'")
                .AND()
                .WHERE(dbColumns.jobName.name() + "='" + jobName.getName() + "'");
        return jdbcTemplate.queryForObject(sql.toString(), mapper);
    }

    @Override
    public List<Job> readAllJobs() {
        SQL sql = new SQL().SELECT("*").FROM(tableName);
        return jdbcTemplate.query(sql.toString(), mapper);
    }

}
