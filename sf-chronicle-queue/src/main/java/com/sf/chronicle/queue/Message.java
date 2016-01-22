package com.sf.chronicle.queue;

import java.io.Serializable;

/**
 * Created by adityasofat on 21/01/2016.
 */
public class Message<T> implements Serializable {
    public long index;
    public T payload;

    public Message(T payload) {
        this.payload = payload;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Message{" +
                "index=" + index +
                ", payload=" + payload +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;

        Message<?> message = (Message<?>) o;

        if (index != message.index) return false;
        return payload.equals(message.payload);

    }

    public T getPayload() {
        return payload;
    }

    @Override
    public int hashCode() {
        int result = (int) (index ^ (index >>> 32));
        result = 31 * result + payload.hashCode();
        return result;
    }
}
