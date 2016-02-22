package com.sf.util.datasource;

import com.sf.util.file.SFClassUtils;
import org.apache.commons.io.FileUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adityasofat on 22/02/2016.
 */
public class TruncateUtilShould {


    @Test
    public void executeDDL() throws URISyntaxException, IOException {
        URL resource = SFClassUtils.getClassLoader().getResource("001_create_table_deltaRun.ddl");
        String fileToString = FileUtils.readFileToString(new File(resource.toURI()));
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceUtil.simpleDatSource());
        jdbcTemplate.execute(fileToString);
        MatcherAssert.assertThat(resource, CoreMatchers.notNullValue());
    }

    @Test
    public  void truncateTable(){
        new TruncateUtil(DataSourceUtil.simpleDatSource()).truncateAllTables("jobExecution","job");
    }


    @Test
    public void dropTables() throws URISyntaxException, IOException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceUtil.simpleDatSource());
      //  List<String> stringList = jdbcTemplate.queryForList(new SQL().SELECT("TABLE_NAME").FROM("information_schema.tables").WHERE("TABLE_SCHEMA='nnbatch'").toString(), String.class);
        List<String> tableList = Arrays.asList("jobExecution","job");
        tableList.forEach(table -> jdbcTemplate.execute("DROP TABLE " + table));
    }

}