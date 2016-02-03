package com.sf.channel;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class PublishChannelShould {

    @Test
    public void publishToChannel() {
        //Given
        String message="Hello";
        List<String> collector = new ArrayList<String>();
        PublishChannel<String> stringChannel = Channels.newPublishChannel("reader");
        //When
        stringChannel.publish(message);
        //Then
        MatcherAssert.assertThat(collector, Matchers.contains(message));
    }

}