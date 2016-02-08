package com.sf.util.security;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * test to determine hash choice and ensure validate hash produce
 */
public class HashUtilShould {

    private String simpleString = "HelloWorld";
    /* 160 bit, 40 characters*/
    private String simpleStringSha1Hash = "db8ac1c259eb89d4a131b253bacfca5f319d54f2";
    /* 128 bit, 32 characters*/
    private String simpleStringMD5Hash = "68e109f0f40ca72a15e05cc22786f8e6";

    private String jsonString = "{\"id\":1,\"name\":\"GCD\",\"server\":\"lonlx102394\",\"port\":\"1023\",\"instanceName\":\"ms1\",\"Component\":\"restService\"}";
    /* 160 bit, 40 characters*/
    private String jsonStringSha1Hash = "0d22e2b600a94f7dd99c6bf98a28e61bb7c96ee2";
    /* 128 bit, 32 characters*/
    private String jsonStringMD5Hash = "1e049636ce26aa66c4829d055a18c0e9";


    @Test
    public void shouldGenerateSHA1Hash() {
        MatcherAssert.assertThat(new HashUtil().hash(simpleString,HashUtil.sha1), CoreMatchers.equalTo(simpleStringSha1Hash));
        MatcherAssert.assertThat(new HashUtil().hash(jsonString,HashUtil.sha1), CoreMatchers.equalTo(jsonStringSha1Hash));
    }

    @Test
    public void shouldGenerateSHA1HashingWithGuava() {
        MatcherAssert.assertThat(Hashing.sha1().hashString(simpleString, Charsets.UTF_8).toString(), CoreMatchers.equalTo(simpleStringSha1Hash));
        MatcherAssert.assertThat(Hashing.sha1().hashString(jsonString, Charsets.UTF_8).toString(), CoreMatchers.equalTo(jsonStringSha1Hash));
    }


    @Test
    public void shouldGenerateMD5Hash() {
        MatcherAssert.assertThat(new HashUtil().hash(simpleString,HashUtil.md5), CoreMatchers.equalTo(simpleStringMD5Hash));
        MatcherAssert.assertThat(new HashUtil().hash(jsonString,HashUtil.md5), CoreMatchers.equalTo(jsonStringMD5Hash));
    }


    /*Chosen to use md5 using Guava*/
    @Test
    public void shouldGenerateMD5HashingWithGuava() {
        MatcherAssert.assertThat(Hashing.md5().hashString(simpleString, Charsets.UTF_8).toString(), CoreMatchers.equalTo(simpleStringMD5Hash));
        MatcherAssert.assertThat(Hashing.md5().hashString(jsonString, Charsets.UTF_8).toString(), CoreMatchers.equalTo(jsonStringMD5Hash));
    }

}