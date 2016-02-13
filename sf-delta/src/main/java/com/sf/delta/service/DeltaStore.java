package com.sf.delta.service;

import com.sf.delta.domain.DeltaRun;

public interface DeltaStore {
    DeltaRun createDeltaRun(Integer deltaRunId, String deltaRunName);

}
