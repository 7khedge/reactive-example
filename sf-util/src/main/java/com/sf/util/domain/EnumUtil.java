package com.sf.util.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by adityasofat on 28/02/2016.
 */
public class EnumUtil {

    public static <E extends Enum<E>> Map<String,Integer> enumParameters(Class<E> enumType) {
        return Arrays.asList(enumType.getEnumConstants()).stream().collect(Collectors.toMap(Enum::name, enumInstance -> 0));
    }
}
