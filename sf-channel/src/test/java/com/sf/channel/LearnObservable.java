package com.sf.channel;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscriber;
import rx.observers.SafeSubscriber;
import rx.schedulers.Schedulers;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by adityasofat on 18/04/16.
 */
public class LearnObservable {

    private Random random = new Random();

    private Logger log = LoggerFactory.getLogger(getClass());
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private Subscriber<Integer> subscriber = new SafeSubscriber<>(new Subscriber<Integer>() {
        @Override
        public void onCompleted() {
            log.info("Received onCompleted event");
            countDownLatch.countDown();
        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onNext(Integer s) {
            log.info("Received message [" + s + "]");
        }
    });

    @Before
    public void setup(){
    }

    private String doIntWork(Integer integer){
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Converting Integer [" + integer + "]");
        return String.valueOf(integer);
    }

    private Integer doStringWork(String string){
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Converting String [" + string + "]");
        return Integer.parseInt(string);
    }


    @Test
    public void shouldSubscriberOnMultipleThreads(){

        Observable<Integer> stringObservable = Observable
                .range(1, 20)
                .map(this::doIntWork)
                .map(this::doStringWork)
                .observeOn(Schedulers.from(Executors.newFixedThreadPool(8)))
                .subscribeOn(Schedulers.from(Executors.newFixedThreadPool(8)))
                ;

        stringObservable.subscribe(subscriber);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
