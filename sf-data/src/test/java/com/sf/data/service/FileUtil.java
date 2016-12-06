package com.sf.data.service;

import com.sf.util.file.SFClassUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by adityasofat on 06/12/2016.
 */
public class FileUtil {

    public static Path getPath(String fileName) throws URISyntaxException {
        URL resource = SFClassUtils.getClassLoader().getResource(fileName);
        MatcherAssert.assertThat("Could not find file", resource, Matchers.notNullValue());
        return Paths.get(resource.toURI());
    }
}
