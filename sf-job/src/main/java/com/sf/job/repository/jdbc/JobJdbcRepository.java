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
    public final static String tableName = "job";
    private enum dbColumns {
        jobId,
        jobGroup,
        jobName,
        dataKey;
    }

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
        return jdbcTemplate.queryForObject(sql.toString(), (rs, rowNum) -> {
            return new Job(rs.getLong(dbColumns.jobId.name()),
                    JobName.valueOf(rs.getString(dbColumns.jobGroup.name()) + "_" + rs.getString(dbColumns.jobName.name())),
                    new IdKey(rs.getString(dbColumns.dataKey.name())));
        });
    }

}
