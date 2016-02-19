package com.sf.channel;

import com.sf.chronicle.queue.MessageUtil;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by adityasofat on 18/02/2016.
 */
public class Adaptors {


    public static Adaptor<String> stringAdaptor(final List<String> messageCollector, final CountDownLatch countDownLatch) {
        return message -> {
            messageCollector.add(message);
            System.out.println(Thread.currentThread().getName() + "Adaptor Received message [" + message + "]");
            if (messageCollector.size() == MessageUtil.getMessages().size()) {
                countDownLatch.countDown();
            }
        };
    }
}
