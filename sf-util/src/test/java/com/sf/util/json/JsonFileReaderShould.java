package com.sf.util.json;

import com.sf.util.file.FileUtil;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class JsonFileReaderShould {

    @Test
    public void readJsonFileToListOfMaps() throws IOException {
        JsonFileReader jsonFileReader = new JsonFileReader();
        List<Map<String, String>> data = jsonFileReader.readJsonArrayFromFile(FileUtil.getClassPathInputStream("ApplicationInstance_1.json"));
        MatcherAssert.assertThat(data.size(), CoreMatchers.equalTo(10));
    }

    @Test( expected = IllegalStateException.class)
    public void throwAnExceptionForNonExistingFile() throws IOException {
        JsonFileReader jsonFileReader = new JsonFileReader();
        jsonFileReader.readJsonArrayFromFile(FileUtil.getClassPathInputStream("NonExistingFile_1.json"));
    }

}