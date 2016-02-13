package com.sf.delta.repository;

import com.sf.delta.domain.DeltaRun;
import com.sf.util.Date.DateUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by adityasofat on 12/02/2016.
 */
public class DeltaRunJdbcRepository implements DeltaRunRepository {

    private SimpleJdbcInsert insertDeltaRun;

    public DeltaRunJdbcRepository(DataSource dataSource) {
        this.insertDeltaRun = new SimpleJdbcInsert(dataSource)
                .withTableName("deltaRun")
                .usingGeneratedKeyColumns("id");
    }

    public void init(){
        this.insertDeltaRun.compile();
    }

    @Override
    public DeltaRun insert(String deltaRunName) {
        LocalDateTime now = LocalDateTime.now();
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("name", deltaRunName);
        parameters.put("startDateTime", DateUtils.asDate(now));
        Number newId = insertDeltaRun.executeAndReturnKey(parameters);
        return new DeltaRun(newId.longValue(), deltaRunName, now, Optional.empty());
    }

}
