package com.sf.kafka;

import org.junit.Test;

/**
 * Created by adityasofat on 15/02/2016.
 */
public class KafkaListenerShould {

    @Test
    public void listenToTopic() {
        KafkaListener<String, String> kafkaListener = new KafkaListener<>("test");
        kafkaListener.subscriber(String::toString);
        kafkaListener.close();
    }

}