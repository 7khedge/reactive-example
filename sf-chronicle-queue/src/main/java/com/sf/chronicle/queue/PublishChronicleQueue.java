package com.sf.chronicle.queue;

import net.openhft.chronicle.ExcerptAppender;
import net.openhft.chronicle.IndexedChronicle;

import java.io.IOException;
import java.util.List;

/**
 * Created by adityasofat on 21/01/2016.
 */
public class PublishChronicleQueue {
    private final IndexedChronicle indexedChronicle;
    private ExcerptAppender excerptAppender;

    public PublishChronicleQueue(IndexedChronicle indexedChronicle) {

        this.indexedChronicle = indexedChronicle;
    }

    public void init() {
        try {
            this.excerptAppender = indexedChronicle.createAppender();
        } catch (IOException e) {
            throw new RuntimeException("Failed to chronicle Queue Appender",e);
        }
    }

    public void close() {
        if ( this.excerptAppender != null) {
            this.excerptAppender.close();
        }
    }

    public <T> void publishMessages(List<Message<T>> messages, int messageSize) {
        for(Message<T> message : messages) {
            publishMessage(message, messageSize);
        }
    }

    public <T> void publishMessage(Message<T> message, int messageSize) {
        // Configure the appender to write up to messageSize bytes
        this.excerptAppender.startExcerpt(messageSize);
        message.setIndex(this.excerptAppender.index());
        // Copy the content of the Object as binary
        this.excerptAppender.writeObject(message);
        // Commit
        this.excerptAppender.finish();
    }

}
