package com.sf.util.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sf.util.file.FileUtil;
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
public class JsonFileReader {
        private Gson gson = new Gson();
        private Type type = new TypeToken<ArrayList<Map<String, String>>>() {}.getType();

        public List<Map<String,String>> readJsonArrayFromFile(InputStream inputStream) {
            checkNotNull(inputStream);
            List<Map<String, String>> data;
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(FileUtil.getClassPathInputStream("ApplicationInstance_1.json")));
                data = gson.fromJson(reader, type);
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException("unable to read Json Array from file  [" + inputStream + "]",e);
            }
            finally {
                IOUtils.closeQuietly(inputStream);
            }
            return data;
        }
}
