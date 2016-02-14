package com.sf.job.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * marker interface for JobExecutionParametersUtil Enum Implementation
 */
public class JobExecutionParametersUtil {

    public static Map<String,String> jobExecutionParametersMap(Enum[] enumOfProperties){
        Map<String,String>  stringStringMap = new HashMap<>();
        Arrays.asList(enumOfProperties).forEach(key -> stringStringMap.put(key.toString(),""));
        return stringStringMap;
    }
}
