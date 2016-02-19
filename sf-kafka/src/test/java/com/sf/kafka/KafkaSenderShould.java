package com.sf.kafka;

import org.junit.Test;

/**
 * Created by adityasofat on 15/02/2016.
 */
public class KafkaSenderShould {

    @Test
    public void publishAMessage(){
        String topicName = "test";
        KafkaSender<String,String> kafkaSender = new KafkaSender<>(topicName);
        kafkaSender.sendMessage("Hello World!");
        kafkaSender.close();
        KafkaListener<String, String> kafkaListener = new KafkaListener<>(topicName);
        kafkaListener.init();
        kafkaListener.subscriber(message -> System.out.println("Message Received [" + message + "]"));
        kafkaListener.close();
    }

}