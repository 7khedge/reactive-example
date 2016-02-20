package com.sf.datasource;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by adityasofat on 20/02/2016.
 */
public class TruncateUtil {

    private final JdbcTemplate jdbcTemplate;

    public TruncateUtil(DataSource  dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void truncateAllTables(String ... tables) {
        for(String table : tables){
            this.jdbcTemplate.execute(new SQL().DELETE_FROM(table).toString());
        }
    }
}
