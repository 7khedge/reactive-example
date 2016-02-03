package com.sf.channel;

import com.sf.chronicle.queue.ChronicleQueue;
import com.sf.chronicle.queue.Message;
import com.sf.chronicle.queue.PersistenceSpaces;
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

        Adaptor<String> stringAdaptor = new  Adaptor<String>() {
            @Override
            public void process(String  message) {
                System.out.println(Thread.currentThread().getName() + "Adaptor Received message [" + message + "]");
            }
        };
        String channelName = "observedChannel";

        ChronicleQueue<String> stringChronicleQueue = new ChronicleQueue<String>(channelName, PersistenceSpaces.persistenceSpace(DEFAULT));
        stringChronicleQueue.init();
        ListenChronicleQueueObserver<String> stringListenChronicleQueueObserver = new ListenChronicleQueueObserver<String>(channelName, stringAdaptor, stringChronicleQueue, -1);
        stringListenChronicleQueueObserver.init();

        stringChronicleQueue.publishMessages(getMessages(),128);


        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await();

    }

    private List<Message<String>> getMessages() {
        List<Message<String>> messages = new ArrayList<Message<String>>();
        messages.add(new Message<String>("message0"));
        messages.add(new Message<String>("message1"));
        messages.add(new Message<String>("message2"));
        messages.add(new Message<String>("message3"));
        messages.add(new Message<String>("message4"));
        return messages;
    }




}