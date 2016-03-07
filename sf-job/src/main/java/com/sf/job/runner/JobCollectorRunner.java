package com.sf.job.runner;

import com.sf.job.definition.JobCollectorDefinition;
import rx.Subscriber;
import rx.observables.ConnectableObservable;
import rx.observers.SafeSubscriber;

/**
 * Created by adityasofat on 08/02/2016.
 */
public class JobCollectorRunner<F,T> implements JobRunner {
    private final JobCollectorDefinition<F, T> jobCollectorDefinition;

    public JobCollectorRunner(JobCollectorDefinition<F,T> jobCollectorDefinition) {
        this.jobCollectorDefinition = jobCollectorDefinition;
    }

    private SafeSubscriber<F> safeSubscriber = new SafeSubscriber<>(new Subscriber<F>() {
        @Override
        public void onCompleted() {
            System.out.println("OnCompleted");
        }
        @Override
        public void onError(Throwable e) {
            System.out.println(e.getCause());
        }
        @Override
        public void onNext(F message) {
            T transformed  = jobCollectorDefinition.getItemReader().readItem(message);
            jobCollectorDefinition.getItemCollectorProcessor().process(transformed);
            jobCollectorDefinition.getItemCollectorWriter().write(transformed);
        }
    });

    @Override
    public void startJob() {
        ConnectableObservable<F> items = jobCollectorDefinition.getItems();
        items.subscribe(safeSubscriber);
        items.connect();
    }

}
