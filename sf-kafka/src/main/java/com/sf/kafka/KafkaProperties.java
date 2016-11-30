package com.sf.kafka;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * Created by adityasofat on 15/02/2016.
 */
public class KafkaProperties {

    public static Properties sender(){
        Properties properties = new Properties();
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());
        addCommon(properties);
        return properties;
    }

    private static void addCommon(Properties properties){
        properties.put("bootstrap.servers", "localhost:9092");
    }

    public static Properties listening(){
        Properties properties = new Properties();
        properties.put("group.id", "test");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());
        addCommon(properties);
        return properties;
    }
}
