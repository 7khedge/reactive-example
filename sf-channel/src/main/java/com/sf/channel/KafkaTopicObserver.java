package com.sf.channel;

import com.sf.kafka.KafkaListener;
import rx.Observable;
import rx.Subscriber;
import rx.observables.ConnectableObservable;
import rx.observers.SafeSubscriber;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * Created by adityasofat on 03/02/2016.
 */
public class KafkaTopicObserver<T> implements ListenChannel<T> {

    private final Adaptor<T> adaptor;
    private final KafkaListener<String,T> kafkaListener;
    private ConnectableObservable<T> observer;
    private Subscriber<T> subscriber;

    public KafkaTopicObserver(KafkaListener<String,T> kafkaListener, Adaptor<T> adaptor) {
        this.adaptor = adaptor;
        this.kafkaListener = kafkaListener;
    }

    @Override
    public void onMessage(T message) {
        this.adaptor.process(message);
    }

    @Override
    public String getName() {
        return this.kafkaListener.getName();
    }

    @Override
    public void init() {
        observer = createObserver(this.kafkaListener);
        kafkaListener.init();
        subscriber = safeSubscriber(this);
        observer.subscribe(subscriber);
        observer.connect();
    }

    @Override
    public void close() {
        subscriber.unsubscribe();
    }

    private Subscriber<T> safeSubscriber(final ListenChannel<T> listenChannel) {
        return new SafeSubscriber<T>(new Subscriber<T>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getCause());
            }

            @Override
            public void onNext(T message) {
                System.out.println(Thread.currentThread().getName() + "Received message [" + message + "]");
                listenChannel.onMessage(message);
            }
        });
    }

    private ConnectableObservable<T> createObserver(final KafkaListener<String,T> kafkaListener) {
        return Observable.create(
                new Observable.OnSubscribe<T>() {
                    @Override
                    public void call(final Subscriber<? super T> sub) {
                        System.out.println("Entered Observer");
                        try {
                            if (sub.isUnsubscribed()) {
                                return;
                            }
                            while (true) {
                                System.out.println(Thread.currentThread().getName() + " In loop to receive messages");
                                kafkaListener.subscriber(message -> {
                                    System.out.println(Thread.currentThread().getName() + "Found  message [" + message + "]");
                                    sub.onNext(message);
                                });
                                if (sub.isUnsubscribed()) {
                                    sub.onCompleted();
                                    break;
                                }
                                TimeUnit.SECONDS.sleep(5);
                            }
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                    }
                }
        )
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.computation())
                .publish();
    }

}
