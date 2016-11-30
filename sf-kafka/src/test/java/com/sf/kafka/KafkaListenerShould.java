package com.sf.kafka;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by adityasofat on 15/02/2016.
 */
public class KafkaListenerShould {

    @Test
  //  @Ignore("Need to understand offset and message expiry")
    public void listenToTopic() {
        KafkaListener<String, String> kafkaListener = new KafkaListener<>("test");
        kafkaListener.subscriber(String::toString);
        kafkaListener.close();
    }

}