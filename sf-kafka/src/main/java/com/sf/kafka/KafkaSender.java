package com.sf.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Created by adityasofat on 15/02/2016.
 */
public class KafkaSender<K,V> {

    private final Producer<K,V> producer;
    private final String topicName;

    public KafkaSender(String topicName) {
        this.topicName = topicName;
        this.producer = new KafkaProducer<>(KafkaProperties.sender());
    }

    public void sendMessage(V message){
        producer.send(new ProducerRecord<>(topicName, message));
    }

    public void close() {
        this.producer.close();
    }
}
