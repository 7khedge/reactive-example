package com.sf.channel;

import com.sf.chronicle.queue.MessageUtil;
import com.sf.kafka.KafkaListener;
import com.sf.kafka.KafkaSender;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by adityasofat on 18/02/2016.
 */
public class KafkaTopicObserverShould {

    @Test
    public void collectMessages() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final List<String> messageCollector = new ArrayList<>();
        Adaptor<String> stringAdaptor = Adaptors.stringAdaptor(messageCollector, countDownLatch);
        String channelName = "test";
        KafkaSender<String,String> kafkaSender = new KafkaSender<>(channelName);
        MessageUtil.getMessages().stream().forEach(message -> kafkaSender.sendMessage(message.getPayload()));
        KafkaListener<String,String> kafkaLisSender = new KafkaListener<>(channelName);
        KafkaTopicObserver<String> kafkaTopicObserver = new KafkaTopicObserver<>(kafkaLisSender, stringAdaptor);
        kafkaTopicObserver.init();
        countDownLatch.await();
        MatcherAssert.assertThat(messageCollector, Matchers.containsInAnyOrder(MessageUtil.getMessagePayLoad().toArray()));
    }

}