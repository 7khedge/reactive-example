package com.sf.job;

import rx.observables.ConnectableObservable;

/**
 * Created by adityasofat on 08/02/2016.
 */
public class JobRunner<F,T,E extends Enum<E>> {
    private final JobDefinition<F, T, E> jobDefinition;

    public JobRunner(JobDefinition<F,T,E> jobDefinition) {
        this.jobDefinition = jobDefinition;
    }


    public void startJob(ConnectableObservable<String> stringObservable) {
        stringObservable.connect();
    }


}
