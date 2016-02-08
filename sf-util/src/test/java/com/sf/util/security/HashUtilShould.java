package com.sf.util.security;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Created by adityasofat on 08/02/2016.
 */
public class HashUtilShould {
    @Test
    public void shouldGenerateHash() {
        String expectedHash = "5e32599a52d03d8c901136650dfbbf37f42b205ddba02c194cf58d61477f48b5";
        String stringToHash = "{\"id\":1,\"name\":\"GCD\",\"server\":\"lonlx102394\",\"port\":\"1023\",\"instanceName\":\"ms1\",\"Component\":\"restService\"}";
        HashUtil hashUtil = new HashUtil();
        hashUtil.init();
        MatcherAssert.assertThat(hashUtil.hash(stringToHash), CoreMatchers.equalTo(expectedHash));
    }
}