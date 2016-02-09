package com.sf.channel;

import com.sf.chronicle.queue.ChronicleQueue;
import com.sf.chronicle.queue.Message;
import com.sf.chronicle.queue.MessageListener;
import rx.Observable;
import rx.Subscriber;
import rx.observables.ConnectableObservable;
import rx.observers.SafeSubscriber;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * Created by adityasofat on 03/02/2016.
 */
public class ChronicleQueueObserver<T> implements ListenChannel<T> {

    private final Adaptor<T> adaptor;
    private final ChronicleQueue<T> chronicleQueue;
    private final int startIndex;
    private ConnectableObservable<T> observer;
    private Subscriber<T> subscriber;

    public ChronicleQueueObserver(ChronicleQueue<T> chronicleQueue, int startIndex, Adaptor<T> adaptor) {
        this.adaptor = adaptor;
        this.chronicleQueue = chronicleQueue;
        this.startIndex = startIndex;
    }

    @Override
    public void onMessage(T message) {
        this.adaptor.process(message);
    }

    @Override
    public String getName() {
        return this.chronicleQueue.getQueueName();
    }

    @Override
    public void init() {
        observer = createObserver(startIndex, chronicleQueue);
        subscriber = safeSubscriber(this);
        observer.subscribe(subscriber);
        observer.connect();
    }

    @Override
    public void close() {
        subscriber.unsubscribe();
    }

    private Subscriber safeSubscriber(final ListenChannel<T> listenChannel) {
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

    private ConnectableObservable<T> createObserver(final int startIndex, final ChronicleQueue<T> chronicleQueue) {
        return Observable.create(
                new Observable.OnSubscribe<T>() {
                    @Override
                    public void call(final Subscriber<? super T> sub) {
                        System.out.println("Entered Observer");
                        try {
                            if (sub.isUnsubscribed()) {
                                return;
                            }
                            long currentIndex = startIndex;
                            while (true) {
                                System.out.println(Thread.currentThread().getName() + " In loop to receive messages");
                                currentIndex = chronicleQueue.readMessages(new MessageListener<T>() {
                                    @Override
                                    public void onMessage(Message<T> message) {
                                        System.out.println(Thread.currentThread().getName() + "Found  message [" + message + "]");
                                        sub.onNext(message.getPayload());
                                    }
                                }, currentIndex);
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
