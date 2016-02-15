package com.sf.util.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class JsonUtil {
    private final Gson gson = new Gson();
    private final Type listOfMapsOfStrings = new TypeToken<ArrayList<Map<String, String>>>() {}.getType();
    private final Type mapOfStrings = new TypeToken<Map<String, String>>() {}.getType();

    public List<Map<String, String>> readJsonArrayFromFile(InputStream inputStream) {
        checkNotNull(inputStream);
        List<Map<String, String>> data;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            data = gson.fromJson(reader, listOfMapsOfStrings);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("unable to read Json Array from file  [" + inputStream + "]", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return data;
    }

    public Map<String, String> readJsonMap(String jsonString) {
        checkNotNull(jsonString);
        return gson.fromJson(jsonString, mapOfStrings);
    }
}
