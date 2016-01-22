package com.sf.channel;

import com.sf.chronicle.queue.ChronicleQueue;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class StringChannel implements Channel<String> {

    private final Adaptor<String> adaptor;
    private final ChronicleQueue queue;

    public StringChannel(Adaptor<String> adaptor, ChronicleQueue queue) {
        this.adaptor = adaptor;
        this.queue = queue;
    }



}
