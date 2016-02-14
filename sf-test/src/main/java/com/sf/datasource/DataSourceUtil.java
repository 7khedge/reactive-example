package com.sf.datasource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;

/**
 * Created by adityasofat on 14/02/2016.
 */
public class DataSourceUtil {


    public static DataSource simpleDatSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/hazel");
        dataSource.setUser("hazeluser");
        dataSource.setPassword("password");
        return dataSource;
    }
}
