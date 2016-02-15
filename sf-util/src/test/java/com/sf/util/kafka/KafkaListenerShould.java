package com.sf.util.kafka;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by adityasofat on 15/02/2016.
 */
public class KafkaListenerShould {

    @Test
    @Ignore
    public void listenToTopic() {
        KafkaListener<String, String> kafkaListener = new KafkaListener<String, String>();
        kafkaListener.subscriber("test");
        kafkaListener.close();
    }

}