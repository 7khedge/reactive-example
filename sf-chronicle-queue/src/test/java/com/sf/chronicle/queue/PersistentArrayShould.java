package com.sf.chronicle.queue;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class PersistentArrayShould {
    PersistenceSpace persistenceSpace = PersistenceSpaces.defaultPersistenceSpace();

    @Before
    public void setup() {
        persistenceSpace.createPersistenceSpace();
    }

    @Test
    public void saveArrayItem() {
        PersistentArray persistentArray = PersistentArrayBuilder.builder()
                .persistentRootDirectory(persistenceSpace.getPath())
                .persistentArrayName("eventQueueIndexes")
                .fileSize(1024)
                .itemSize(14)
                .build();
        persistentArray.init();
        persistentArray.put(getQueueEntry(0, 0).getBytes(), 0);
        persistentArray.put(getQueueEntry(0, 1).getBytes(), 0);
        persistentArray.put(getQueueEntry(1, 0).getBytes(), 1);
        persistentArray.put(getQueueEntry(1, 1).getBytes(), 1);
        persistentArray.put(getQueueEntry(1, 2).getBytes(), 1);
        persistentArray.close();
        PersistentArray persistentArrayRead = PersistentArrayBuilder.builder()
                .persistentRootDirectory(persistenceSpace.getPath())
                .persistentArrayName("eventQueueIndexes")
                .fileSize(1024)
                .itemSize(14)
                .build();
        persistentArrayRead.init();
        String queue0 = new String(persistentArrayRead.read(0));
        String queue1 = new String(persistentArrayRead.read(1));
        persistentArrayRead.close();
        MatcherAssert.assertThat(queue0, CoreMatchers.equalTo("queue00=000001"));
        MatcherAssert.assertThat(queue1, CoreMatchers.equalTo("queue01=000002"));
    }

    private String getQueueEntry(int queueNumber, int index) {
        return String.format("queue%02d=%06d", queueNumber, index);
    }
}
