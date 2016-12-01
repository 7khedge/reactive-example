package com.sf.data.service;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Created by adityasofat on 01/12/2016.
 */
public class MessageStringCleanerTest {

    private MessageStringCleaner messageStringCleaner = new MessageStringCleaner();

    @Test
    public void shouldRemoveQuotes() throws Exception {
        //Given
        String string = "\"hello\"";
        //When
        string = messageStringCleaner.cleanString(string);
        //Then
        MatcherAssert.assertThat(string, Matchers.equalTo("hello"));
    }


    @Test
    public void shouldReplaceWithEmptyString() throws Exception {
        //Given
        String string = "\\N";
        //When
        string = messageStringCleaner.cleanString(string);
        //Then
        MatcherAssert.assertThat(string.isEmpty(), Matchers.equalTo(true));
    }

}