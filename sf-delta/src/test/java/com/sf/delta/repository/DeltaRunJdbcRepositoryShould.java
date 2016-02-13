package com.sf.delta.repository;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.sf.delta.domain.DeltaRun;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import javax.sql.DataSource;

/**
 * Created by adityasofat on 12/02/2016.
 */
public class DeltaRunJdbcRepositoryShould {

    @Test
    public void createDeltaRecord(){
        //Given
        String deltaRunName = "ApplicationInstance";
        DeltaRunJdbcRepository  deltaRunRepository = new DeltaRunJdbcRepository(dataSource());
        deltaRunRepository.init();
        DeltaRun deltaRun = deltaRunRepository.insert(deltaRunName);
        MatcherAssert.assertThat(deltaRun.getDeltaRunName(), CoreMatchers.equalTo(deltaRunName));
    }

    public static DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/hazel");
        dataSource.setUser("hazeluser");
        dataSource.setPassword("password");
        return dataSource;
    }


}