package com.sf.chronicle.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adityasofat on 02/02/2016.
 */
public class MessageUtil {

    public static List<Message<String>> getMessages() {
        List<Message<String>> messages = new ArrayList<Message<String>>();
        messages.add(new Message<String>("message0"));
        messages.add(new Message<String>("message1"));
        messages.add(new Message<String>("message2"));
        messages.add(new Message<String>("message3"));
        messages.add(new Message<String>("message4"));
        return messages;
    }

}
