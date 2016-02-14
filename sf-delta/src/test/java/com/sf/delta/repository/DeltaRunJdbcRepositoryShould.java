package com.sf.delta.repository;


import com.sf.delta.domain.DeltaRun;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static com.sf.datasource.DataSourceUtil.simpleDatSource;

/**
 * Created by adityasofat on 12/02/2016.
 */
public class DeltaRunJdbcRepositoryShould {

    @Test
    public void createDeltaRecord(){
        //Given
        String deltaRunName = "ApplicationInstance";
        DeltaRunJdbcRepository  deltaRunRepository = new DeltaRunJdbcRepository(simpleDatSource());
        deltaRunRepository.init();
        DeltaRun deltaRun = deltaRunRepository.insert(deltaRunName);
        MatcherAssert.assertThat(deltaRun.getDeltaRunName(), CoreMatchers.equalTo(deltaRunName));
    }

}