package com.sf.chronicle.queue;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.sf.chronicle.queue.PersistenceSpaces.DEFAULT;
import static org.hamcrest.Matchers.containsInAnyOrder;


/**
 * Created by adityasofat on 21/01/2016.
 */
public class ChronicleQueueShould {

    //Given environment
    private PersistenceSpace persistenceSpace;
    private ChronicleQueue chronicleQueue;
    private int messageSize = 164;


    @Before
    public void setup() {
        persistenceSpace = PersistenceSpaces.persistenceSpace(false, DEFAULT);
        chronicleQueue = new ChronicleQueue("eventQueue", persistenceSpace);
        chronicleQueue.init();
    }

    @After
    public void tearDown() {
        chronicleQueue.close();
      //  persistenceSpace.removePersistenceSpace();
    }

    @Test
    public void shouldPublishMessages() {
        //Given
        //When
        try {
            chronicleQueue.publishMessages(MessageUtil.getMessages(),messageSize);
        }catch(RuntimeException e){
            MatcherAssert.assertThat("Exception found " + e.getMessage(), false, CoreMatchers.equalTo(true));
        }
    }

    @Test
    public void shouldPublishAMessage() {
        //Given
        //When
        try {
            chronicleQueue.publishMessage(MessageUtil.getMessages().get(0),messageSize);
        }catch(RuntimeException e){
            MatcherAssert.assertThat("Exception found " + e.getMessage(), false, CoreMatchers.equalTo(true));
        }
    }

    @Test
    public void shouldPublishAndAReadMessage() {
        //Given
        Message<String> expectedMessage = getReadMessages(MessageUtil.getMessages()).get(0);
        final List<Message<String>> actualMessages = new ArrayList<Message<String>>();
        //When
        chronicleQueue.publishMessage(MessageUtil.getMessages().get(0), messageSize);
        chronicleQueue.readMessages(message -> {
            actualMessages.add(message);
            System.out.println(message.toString());
        }, -1);
        //Then
        MatcherAssert.assertThat(expectedMessage, CoreMatchers.is(actualMessages.get(0)));
    }

    @Test
    public void shouldReadMessagesPublishedBeforeSetup() {
        //Given
        List<Message<String>> expectedMessages = getReadMessages(MessageUtil.getMessages());
        final List<Message<String>> actualMessages = new ArrayList<Message<String>>();
        //When
        chronicleQueue.publishMessages(MessageUtil.getMessages(), messageSize);
        chronicleQueue.readMessages(message -> {
            actualMessages.add(message);
            System.out.println(message.toString());
        }, -1);

        //Then
        MatcherAssert.assertThat(expectedMessages, containsInAnyOrder(actualMessages.toArray()));
    }

    @Test
    @Ignore("Fixing Chronicle to be reactive")
    public void shouldReadMessagesAfterSetup() {
        //Given
        List<Message<String>> expectedMessages = getReadMessages(MessageUtil.getMessages());
        final List<Message<String>> actualMessages = new ArrayList<Message<String>>();
        //When
        chronicleQueue.readMessages(message -> {
            actualMessages.add(message);
            System.out.println(message.toString());
        }, -1);
        chronicleQueue.publishMessages(MessageUtil.getMessages(), messageSize);
        //Then
        MatcherAssert.assertThat(expectedMessages, containsInAnyOrder(actualMessages.toArray()));
    }


    private List<Message<String>> getReadMessages(List<Message<String>> messages) {
        int index = 0;
        for(Message<String> message : messages) {
            message.setIndex(index++);
        }
        return messages;
    }


}