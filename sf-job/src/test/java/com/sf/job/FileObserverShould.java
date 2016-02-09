package com.sf.job;

import com.sf.util.file.FileUtil;
import org.junit.Test;
import rx.observables.ConnectableObservable;

/**
 * Created by adityasofat on 09/02/2016.
 */
public class FileObserverShould {

    @Test
    public void emitAllLinesFromFile() {
        FileObserver fileObserver = new FileObserver();
        ConnectableObservable<String> fileObservable = fileObserver.createObserver(FileUtil.getClassPathInputStream("1_ApplicationInstance.json"));




    }



}