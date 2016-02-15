package com.sf.util.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import static com.sf.util.kafka.KafkaProperties.sender;

/**
 * Created by adityasofat on 15/02/2016.
 */
public class KafkaSender<K,V> {

    private final Producer<K,V> producer;

    public KafkaSender() {
        this.producer = new KafkaProducer<K,V>(sender());
    }

    public void sendMessage(String topicName, V message){
        producer.send(new ProducerRecord<K, V>(topicName, message));
    }

    public void close() {
        this.producer.close();
    }
}
