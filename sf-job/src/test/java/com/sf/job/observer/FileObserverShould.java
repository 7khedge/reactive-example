package com.sf.job.observer;

import com.sf.job.observer.FileObserver;
import com.sf.util.file.FileUtil;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import rx.Subscriber;
import rx.observables.ConnectableObservable;
import rx.observers.SafeSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adityasofat on 09/02/2016.
 */
public class FileObserverShould {

    @Test
    public void emitAllLinesFromFile() throws InterruptedException {
        final List<String> stringList = new ArrayList<>();
        FileObserver fileObserver = new FileObserver();
        ConnectableObservable<String> fileObservable = fileObserver.createObserver(FileUtil.getClassPathInputStream("2_ApplicationInstance.json"));
        SafeSubscriber<String> stringSafeSubscriber = new SafeSubscriber<>(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("OnCompleted");
            }
            @Override
            public void onError(Throwable e) {
                System.out.println(e.getCause());
            }
            @Override
            public void onNext(String message) {
                stringList.add(message);
                System.out.println(Thread.currentThread().getName() + "Received message [" + message + "]");
            }
        });
        fileObservable.subscribe(stringSafeSubscriber);
        fileObservable.connect();
        MatcherAssert.assertThat(stringList.size(), CoreMatchers.equalTo(10));
    }



}