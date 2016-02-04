package com.sf.chronicle.queue;

import net.openhft.chronicle.ExcerptTailer;
import net.openhft.chronicle.IndexedChronicle;

import java.io.IOException;

/**
 * Created by adityasofat on 21/01/2016.
 */
public class ListenChronicleQueue<T> {

    private final IndexedChronicle indexedChronicle;
    private ExcerptTailer excerptTailer;

    public ListenChronicleQueue(IndexedChronicle indexedChronicle) {
        this.indexedChronicle = indexedChronicle;
    }

    public void init() {
        try {
            this.excerptTailer = indexedChronicle.createTailer();
        } catch (IOException e) {
            throw new RuntimeException("Failed to chronicle Queue Tailer", e);
        }
    }

    public void close() {
        if (this.excerptTailer != null) {
            this.excerptTailer.close();
        }
    }

    public long listen(MessageListener<T> messageListener, long fromIndex) {
        // While until there is a new Excerpt to read
        long currentIndex = fromIndex;
        this.excerptTailer.index(currentIndex);
        while (this.excerptTailer.nextIndex()) {
            // Read the object
            Message<T> actual = (Message<T>) this.excerptTailer.readObject();
            messageListener.onMessage(actual);
            currentIndex = actual.index;
            // Make the reader ready for next read
            this.excerptTailer.finish();
        }
        return currentIndex;
    }
}
