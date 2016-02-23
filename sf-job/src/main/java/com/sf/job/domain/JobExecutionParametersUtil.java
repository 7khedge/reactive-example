package com.sf.job.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * marker interface for JobExecutionParametersUtil Enum Implementation
 */
public class JobExecutionParametersUtil {

    public static Map<String,Integer> jobExecutionParametersMap(Enum[] enumOfProperties){
        final Map<String,Integer>  stringIntegerMap = new HashMap<>();
        Arrays.asList(enumOfProperties).forEach(key -> stringIntegerMap.put(key.toString(),0));
        return stringIntegerMap;
    }
}
