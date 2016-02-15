package com.sf.util.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;

import static com.sf.util.kafka.KafkaProperties.listening;

/**
 * Created by adityasofat on 15/02/2016.
 */
public class KafkaListener<K, V> {

    private final Consumer<K, V> consumer;

    public KafkaListener() {
        this.consumer = new KafkaConsumer<K, V>(listening());
    }

    public void     subscriber(String topicName) {
        consumer.subscribe(Arrays.asList(topicName));
        while (true) {
            ConsumerRecords<K, V> records = consumer.poll(100);
            for (ConsumerRecord<K, V> record : records)
                System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
        }
    }

    public void close() {
        consumer.unsubscribe();
        consumer.close();
    }

}