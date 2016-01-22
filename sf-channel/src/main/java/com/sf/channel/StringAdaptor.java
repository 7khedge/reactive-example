package com.sf.channel;

import java.util.List;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class StringAdaptor implements Adaptor<String> {

    private final List<String> collector;

    public StringAdaptor(List<String> collector) {
        this.collector = collector;
    }

    public void process(String s) {
        collector.add(s);
    }
}
