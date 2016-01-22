package com.sf.chronicle.queue;

import net.openhft.chronicle.IndexedChronicle;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by adityasofat on 21/01/2016.
 */
public class ChronicleQueue {

    private final String queueName;
    private final PersistenceSpace persistenceSpace;
    private final String queuePath;
    private IndexedChronicle indexedChronicle;
    private PublishChronicleQueue publishChronicleQueue;
    private ListenChronicleQueue listenChronicleQueue;

    public ChronicleQueue(String queueName, PersistenceSpace persistenceSpace) {
        this.queueName = queueName;
        this.persistenceSpace = persistenceSpace;
        this.queuePath = this.persistenceSpace.getPath() + File.separator + queueName;
    }

    public String getQueueName() {
        return queueName;
    }

    public void init(){
        try {
            indexedChronicle = new IndexedChronicle(queuePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create Chronicle Queue [" + queuePath + "]");
        }
        publishChronicleQueue = new PublishChronicleQueue(indexedChronicle);
        publishChronicleQueue.init();
        listenChronicleQueue = new ListenChronicleQueue(indexedChronicle);
        listenChronicleQueue.init();
    }

    public <T> void publishMessage(Message<T> message, int messageSize) {
        publishChronicleQueue.publishMessage(message, messageSize);
    }

    public <T> void publishMessages(List<Message<T>> messages, int messageSize) {
        publishChronicleQueue.publishMessages(messages, messageSize);
    }

    public void readMessages(MessageListener messageListener, Integer fromIndex) {
        listenChronicleQueue.listen(messageListener,fromIndex);
    }

    public void close(){
        publishChronicleQueue.close();
        listenChronicleQueue.close();
        try {
            if ( indexedChronicle != null) {
                indexedChronicle.checkNotClosed();
                indexedChronicle.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to close Chronicle Queue [" + queuePath + "]",e);
        } catch (IllegalStateException ise) {
            throw new RuntimeException("Failed to close Chronicle Queue [" + queuePath + "] as it is already closed",ise);
        }
    }
}
