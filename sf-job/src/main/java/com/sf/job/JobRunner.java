package com.sf.job;

import rx.Subscriber;
import rx.observables.ConnectableObservable;
import rx.observers.SafeSubscriber;

/**
 * Created by adityasofat on 08/02/2016.
 */
public class JobRunner<F,T,E extends Enum<E>> {
    private final JobDefinition<F, T, E> jobDefinition;

    public JobRunner(JobDefinition<F,T,E> jobDefinition) {
        this.jobDefinition = jobDefinition;
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
            T transformed  = jobDefinition.getItemReader().readItem(message);
            jobDefinition.getItemProcessor().process(transformed);
            jobDefinition.getItemWriter().write(transformed);
        }
    });

    public void startJob() {
        ConnectableObservable<F> items = jobDefinition.getItems();
        items.subscribe(safeSubscriber);
        items.connect();
    }

}
