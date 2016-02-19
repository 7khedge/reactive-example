package com.sf.channel;

import com.sf.chronicle.queue.ChronicleQueue;
import com.sf.chronicle.queue.ChronicleQueues;
import com.sf.chronicle.queue.MessageUtil;
import com.sf.chronicle.queue.PersistenceSpaces;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static com.sf.chronicle.queue.PersistenceSpaces.DEFAULT;

/**
 * Created by adityasofat on 03/02/2016.
 */
public class ListenChronicleQueueObserverShould {

    @Test
    public void collectMessages() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final List<String> messageCollector = new ArrayList<>();
        Adaptor<String> stringAdaptor = Adaptors.stringAdaptor(messageCollector, countDownLatch);
        String channelName = "observedChannel";
        ChronicleQueue<String> stringChronicleQueue = ChronicleQueues.<String>newObservableQueue(channelName, PersistenceSpaces.persistenceSpace(DEFAULT));
        ChronicleQueueObserver<String> stringChronicleQueueObserver = new ChronicleQueueObserver<>(stringChronicleQueue, -1, stringAdaptor);
        stringChronicleQueueObserver.init();
        stringChronicleQueue.publishMessages(MessageUtil.getMessages(),128);
        countDownLatch.await();
        MatcherAssert.assertThat(messageCollector, Matchers.containsInAnyOrder(MessageUtil.getMessagePayLoad().toArray()));
    }

}