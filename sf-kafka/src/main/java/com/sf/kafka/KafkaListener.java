package com.sf.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by adityasofat on 15/02/2016.
 */
public class KafkaListener<K, V> {

    private final Consumer<K, V> consumer;
    private final String name;

    public KafkaListener(String name) {
        this.name = name;
        this.consumer = new KafkaConsumer<>(KafkaProperties.listening());
    }

    public void init() {
        consumer.subscribe(Collections.singletonList(name));
    }

    public void subscriber(MessageListener<V> messageListener) {
        ConsumerRecords<K, V> records = consumer.poll(100);
        for (ConsumerRecord<K, V> record : records) {
            messageListener.onMessage(record.value());
        }
        //System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
    }

    public String getName() {
        return name;
    }

    public void close() {
        consumer.unsubscribe();
        consumer.close();
    }

}