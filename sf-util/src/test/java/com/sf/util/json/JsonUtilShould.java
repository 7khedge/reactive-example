package com.sf.util.json;

import com.sf.util.file.FileUtil;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class JsonUtilShould {

    @Test
    public void readJsonFileToListOfMaps() throws IOException {
        JsonUtil jsonUtil = new JsonUtil();
        List<Map<String, String>> data = jsonUtil.readJsonArrayFromFile(FileUtil.getClassPathInputStream("1_ApplicationInstance.json"));
        MatcherAssert.assertThat(data.size(), CoreMatchers.equalTo(10));
    }

    @Test
    public void readJsonFileLineToMapOfStrings() throws IOException {
        JsonUtil jsonUtil = new JsonUtil();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(FileUtil.getClassPathInputStream("2_ApplicationInstance.json")));
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            data.add(jsonUtil.readJsonMap(line));
        }
        bufferedReader.close();
        MatcherAssert.assertThat(data.size(), CoreMatchers.equalTo(10));
    }

    @Test( expected = IllegalStateException.class)
    public void throwAnExceptionForNonExistingFile() throws IOException {
        JsonUtil jsonUtil = new JsonUtil();
        jsonUtil.readJsonArrayFromFile(FileUtil.getClassPathInputStream("NonExistingFile_1.json"));
    }

}