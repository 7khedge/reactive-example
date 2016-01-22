package com.sf.channel;

import org.junit.Test;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class ChannelsTest {

    @Test
    public void createAChannel() {
        //Given
        String channelName = "reader";
        //When
        Channel channel = Channels.newStringChannel(channelName);
    }
}