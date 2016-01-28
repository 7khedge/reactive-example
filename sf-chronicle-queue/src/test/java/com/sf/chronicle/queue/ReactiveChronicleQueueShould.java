package com.sf.chronicle.queue;


import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.observers.SafeSubscriber;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/**
 * Created by adityasofat on 27/01/2016.
 */
public class ReactiveChronicleQueueShould {

    //Given environment
    private PersistenceSpace persistenceSpace;
    private ChronicleQueue chronicleQueue;
    private int messageSize = 256;


    @Before
    public void setup() {
        persistenceSpace = PersistenceSpaces.defaultPersistenceSpace();
        chronicleQueue = new ChronicleQueue("eventQueue", persistenceSpace);
        chronicleQueue.init();
    }

    @After
    public void tearDown() {
        chronicleQueue.close();
        persistenceSpace.removePersistenceSpace();
    }


    @Test
    public void shouldPublishToChronicleQueueFromThread() throws InterruptedException {
        int numberOfMessages = 5;
        reactivePublishMessages(numberOfMessages).await();

        final List<Message<Long>> actualMessages = new ArrayList<Message<Long>>();
        //When
        chronicleQueue.readMessages(new MessageListener<Long>() {
            public void onMessage(Message<Long> message) {
                actualMessages.add(message);
                System.out.println(message.toString());
            }
        }, -1);
        //Then
        MatcherAssert.assertThat(actualMessages.size(), Matchers.equalTo(numberOfMessages));
        MatcherAssert.assertThat(actualMessages, Matchers.containsInAnyOrder(getMessages(numberOfMessages).toArray()));
    }

    private CountDownLatch reactivePublishMessages(int numberOfMessages) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(numberOfMessages);

        //Reactive Publisher
        Observable
                .interval(1L, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onNext(Long aLong) {
                        countDownLatch.countDown();
                        System.out.println(Thread.currentThread().getName() + "Publishing message [" + aLong + "]");
                        chronicleQueue.publishMessage(new Message<Long>(aLong),messageSize);
                    }
                });

        return countDownLatch;
    }


    private List<Message<Long>> getMessages(int numberOfMessages) {
        List<Message<Long>> messages = new ArrayList<Message<Long>>();
        for (long i=0; i< numberOfMessages; i++){
            Message<Long> message = new Message<Long>(i);
            message.setIndex(i);
            messages.add(message);
        }
        return messages;
    }


    @Test
    public void reactiveSubscriptionFromQueue() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final int numberOfMessages = 5;
        //Reactive Subscriber/Observer
        Subscriber<Long> longSafeSubscriber = new SafeSubscriber<Long>(new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getCause());
            }

            @Override
            public void onNext(Long s) {
                System.out.println(Thread.currentThread().getName() + "Received message [" + s + "]");
            }
        });
        final long startIndex = -1;

        Observable<Long> myObservable = Observable.create(
                new Observable.OnSubscribe<Long>() {
                    @Override
                    public void call(final Subscriber<? super Long> sub) {
                        System.out.println("Entered Observer");
                        try {
                            if (sub.isUnsubscribed() ){
                                return;
                            }
                            long currentIndex = startIndex;
                            while(true){
                                System.out.println("In loop to receive messages");
                                currentIndex = chronicleQueue.readMessages(new MessageListener<Long>() {
                                    @Override
                                    public void onMessage(Message<Long> message) {
                                        sub.onNext(message.getPayload());
                                    }
                                }, currentIndex);
                                if (sub.isUnsubscribed()) {
                                    sub.onCompleted();
                                    break;
                                }
                                if ( currentIndex >= numberOfMessages) {
                                    sub.unsubscribe();
                                    countDownLatch.countDown();
                                }
                                TimeUnit.SECONDS.sleep(1);
                            }

                        } catch (InterruptedException e) {
                           System.out.println(e);
                        } finally {
                            countDownLatch.countDown();
                        }
                    }
                }
        )
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.computation());

        myObservable.subscribe(longSafeSubscriber);
        reactivePublishMessages(numberOfMessages).await();
        countDownLatch.await();
    }

}