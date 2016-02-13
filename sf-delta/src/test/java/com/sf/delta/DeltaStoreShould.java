package com.sf.delta;

import com.sf.delta.domain.DeltaRecord;
import com.sf.delta.domain.DeltaRun;
import com.sf.delta.service.DefaultDeltaStore;
import com.sf.delta.service.DeltaStore;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Created by adityasofat on 10/02/2016.
 */
public class DeltaStoreShould {

    @Test
    public void createADeltaRun() {
        //Given
        String deltaRunName = "ApplicationInstance";
        Integer deltaRunId = 1;
        DeltaStore deltaStore = new DefaultDeltaStore();
        //When
        DeltaRun deltaRun = deltaStore.createDeltaRun(deltaRunId,deltaRunName);
        //Then
        MatcherAssert.assertThat(deltaRun.getDeltaRunName(), CoreMatchers.equalTo(deltaRunName));
    }



}
