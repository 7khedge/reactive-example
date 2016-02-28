package com.sf.jobs.spring.context.jobs;

import com.sf.job.observer.FileObserver;
import com.sf.util.file.FileUtil;
import rx.observables.ConnectableObservable;

/**
 * Created by adityasofat on 28/02/2016.
 */
public class ItemObservable {

    public static  ConnectableObservable<String> connectableObservable(String fileName){
        return new FileObserver().createObserver(FileUtil.getClassPathInputStream(fileName));
    }
}
