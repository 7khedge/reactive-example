package com.sf.util.file;

import com.google.common.base.Preconditions;

import java.io.InputStream;

import static com.sf.util.file.SFClassUtils.getClassLoader;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class FileUtil {


    public static InputStream getClassPathInputStream(String relativeClassPath) {
        Preconditions.checkArgument(relativeClassPath != null && !relativeClassPath.isEmpty(),"enter valid relativeClassPath Error: empty");
        InputStream inputStream  = Preconditions.checkNotNull(getClassLoader(),"Current Thread ClassLoader is null").getResourceAsStream(relativeClassPath);
        Preconditions.checkState(inputStream != null, "Could not find file [" + relativeClassPath + "]");
        return inputStream;
    }
}
