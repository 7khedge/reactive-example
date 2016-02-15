package com.sf.util.kafka;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by adityasofat on 15/02/2016.
 */
public class KafkaSenderShould {

    @Test
    @Ignore
    public void publishAMessage(){
        KafkaSender<String,String> kafkaSender =  new KafkaSender<String, String>();
        kafkaSender.sendMessage("test","Hello World!");
        kafkaSender.close();
    }

}