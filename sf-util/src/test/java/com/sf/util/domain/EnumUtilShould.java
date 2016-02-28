package com.sf.util.domain;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adityasofat on 28/02/2016.
 */
public class EnumUtilShould {


    enum TestEnum{ one,two };

    @Test
    public void returnMap(){
        //Given
        Map<String,Integer> expectedMap = new HashMap<>();
        expectedMap.put("one",0);
        expectedMap.put("two",0);
        //When
        Map<String, Integer> actualMap = EnumUtil.enumParameters(TestEnum.class);
        MatcherAssert.assertThat(actualMap, CoreMatchers.equalTo(expectedMap));
    }


}