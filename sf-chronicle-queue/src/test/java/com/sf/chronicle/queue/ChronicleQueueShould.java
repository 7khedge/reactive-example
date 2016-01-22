package com.sf.chronicle.queue;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        persistenceSpace = PersistenceSpaces.defaultPersistenceSpace();
        chronicleQueue = new ChronicleQueue("eventQueue", persistenceSpace);
        chronicleQueue.init();
    }

    @After
    public void tearDown() {
        chronicleQueue.close();
        persistenceSpace.removePersistenceSpace();
    }

    @Test
    public void shouldPublishMessages() {
        //Given
        //When
        try {
            chronicleQueue.publishMessages(getMessages(),messageSize);
        }catch(RuntimeException e){
            MatcherAssert.assertThat("Exception found " + e.getMessage(), false, CoreMatchers.equalTo(true));
        }
    }

    @Test
    public void shouldPublishAMessage() {
        //Given
        //When
        try {
            chronicleQueue.publishMessage(getMessages().get(0),messageSize);
        }catch(RuntimeException e){
            MatcherAssert.assertThat("Exception found " + e.getMessage(), false, CoreMatchers.equalTo(true));
        }
    }

    @Test
    public void shouldPublishAndAReadMessage() {
        //Given
        Message<String> expectedMessage = getReadMessages(getMessages()).get(0);
        final List<Message<String>> actualMessages = new ArrayList<Message<String>>();
        //When
        chronicleQueue.publishMessage(getMessages().get(0), messageSize);
        chronicleQueue.readMessages(new MessageListener() {
            public void onMessage(Message message) {
                actualMessages.add(message);
                System.out.println(message.toString());
            }
        }, -1);
        //Then
        MatcherAssert.assertThat(expectedMessage, CoreMatchers.is(actualMessages.get(0)));
    }

    @Test
    public void shouldPublishAndReadMessages() {
        //Given
        List<Message<String>> expectedMessages = getReadMessages(getMessages());
        final List<Message<String>> actualMessages = new ArrayList<Message<String>>();
        //When
        chronicleQueue.publishMessages(getMessages(), messageSize);
        chronicleQueue.readMessages(new MessageListener() {
            public void onMessage(Message message) {
                actualMessages.add(message);
                System.out.println(message.toString());
            }
        }, -1);
        //Then
        MatcherAssert.assertThat(expectedMessages, containsInAnyOrder(actualMessages.toArray()));
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

    private List<Message<String>> getReadMessages(List<Message<String>> messages) {
        int index = 0;
        for(Message<String> message : messages) {
            message.setIndex(index++);
        }
        return messages;
    }


}