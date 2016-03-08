package com.sf.jobx.domain;

/**
 * Created by adityasofat on 08/02/2016.
 */
public class JsonRecord {
    private final String id;
    private final String payload;

    public JsonRecord(String id, String payload) {
        this.id = id;
        this.payload = payload;
    }

    public String getId() {
        return id;
    }

    public String getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "JsonRecord{" +
                "id='" + id + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }
}
