package com.sf.util.datasource;

import org.junit.Test;

/**
 * Created by adityasofat on 22/02/2016.
 */
public class TruncateUtilShould {

    private TruncateUtil truncateUtil = new TruncateUtil(DataSourceUtil.simpleDatSource());

    @Test
    public void runAllDDLScripts() {
        truncateUtil.executeAllDDL();
    }

    @Test
    public void dropAllTables() {
        truncateUtil.dropAllTables();
    }

}