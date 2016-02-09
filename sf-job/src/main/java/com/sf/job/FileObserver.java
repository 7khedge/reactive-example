package com.sf.job;

import rx.Observable;
import rx.Subscriber;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

import java.io.*;

/**
 * Created by adityasofat on 08/02/2016.
 */
public class FileObserver {

    public FileObserver() {
    }

    public ConnectableObservable<String> createObserver(final InputStream inputStream) {
        return from(new BufferedReader(new InputStreamReader(inputStream)));
    }

    private ConnectableObservable<String> from(final BufferedReader reader) {
        return Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(final Subscriber<? super String> subscriber) {
                        try {
                            String line;
                            while (!subscriber.isUnsubscribed()) {
                                line = reader.readLine();
                                if (line == null) {
                                    break;
                                }
                                if(!line.isEmpty())
                                    subscriber.onNext(line);
                            }
                            if ( !subscriber.isUnsubscribed()) {
                                subscriber.onCompleted();
                            }
                        } catch (IOException e) {
                            if ( !subscriber.isUnsubscribed()){
                                subscriber.onError(e);
                            }
                        }
                    }
                }
        )
                .publish();
    }
}
