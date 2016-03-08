package com.sf.jobx.runner;

import com.sf.jobx.config.JobConfig;
import rx.Subscriber;
import rx.observables.ConnectableObservable;
import rx.observers.SafeSubscriber;

/**
 * Created by adityasofat on 08/02/2016.
 */
public class SimpleJobRunner<F,T> implements JobRunner {
    private final JobConfig<F, T> jobConfig;

    public SimpleJobRunner(JobConfig<F,T> jobConfig) {
        this.jobConfig = jobConfig;
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
            T transformed  = jobConfig.getItemReader().readItem(message);
            jobConfig.getItemProcessor().process(transformed);
            jobConfig.getItemWriter().write(transformed);
        }
    });

    @Override
    public void startJob() {
        ConnectableObservable<F> items = jobConfig.getItems();
        items.subscribe(safeSubscriber);
        items.connect();
    }

}
