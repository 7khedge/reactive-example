package com.sf.util.file;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class FileUtilShould {
    @Test
    public void readAFileFromTheClassPath(){
        InputStream classPathInputStream = FileUtil.getClassPathInputStream("classpath_file.txt");
        MatcherAssert.assertThat("Null InputStream",classPathInputStream, CoreMatchers.notNullValue());
    }

    @Test(expected = IllegalStateException.class)
    public void throwsAnExceptionWhenFileNotOnClasspath(){
        FileUtil.getClassPathInputStream("NonExistingClasspath_file.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsAnExceptionWhenFileNameEmpty() {
        FileUtil.getClassPathInputStream("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsAnExceptionWhenFileNameNull() {
        FileUtil.getClassPathInputStream(null);
    }
}