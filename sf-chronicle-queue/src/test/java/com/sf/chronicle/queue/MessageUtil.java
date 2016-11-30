package com.sf.chronicle.queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adityasofat on 02/02/2016.
 */
public class MessageUtil {

    public static List<String> getMessagePayLoad() {
        return Arrays.asList("message0","message1","message2","message3","message4");
    }

    public static List<Message<String>> getMessages() {
        List<Message<String>> messages = new ArrayList<Message<String>>();
        messages.add(new Message<>(getMessagePayLoad().get(0)));
        messages.add(new Message<>(getMessagePayLoad().get(1)));
        messages.add(new Message<>(getMessagePayLoad().get(2)));
        messages.add(new Message<>(getMessagePayLoad().get(3)));
        messages.add(new Message<>(getMessagePayLoad().get(4)));
        return messages;
    }

}
