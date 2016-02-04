package com.sf.chronicle.queue;

import net.openhft.chronicle.IndexedChronicle;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by adityasofat on 21/01/2016.
 */
public class ChronicleQueue<T> {

    private final String queueName;
    private final String queuePath;
    private IndexedChronicle indexedChronicle;
    private PublishChronicleQueue publishChronicleQueue;
    private ListenChronicleQueue<T> listenChronicleQueue;

    public ChronicleQueue(String queueName, PersistenceSpace persistenceSpace) {
        this.queueName = queueName;
        this.queuePath = persistenceSpace.getPath() + File.separator + queueName;
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
        listenChronicleQueue = new ListenChronicleQueue<T>(indexedChronicle);
        listenChronicleQueue.init();
    }

    public void publishMessage(Message<T> message, int messageSize) {
        publishChronicleQueue.publishMessage(message, messageSize);
    }

    public void publishMessages(List<Message<T>> messages, int messageSize) {
        publishChronicleQueue.publishMessages(messages, messageSize);
    }

    public long readMessages(MessageListener<T> messageListener, long fromIndex) {
        return listenChronicleQueue.listen(messageListener,fromIndex);
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
