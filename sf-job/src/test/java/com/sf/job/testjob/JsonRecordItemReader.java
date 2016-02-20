package com.sf.job.testjob;


import com.sf.job.domain.IdKey;
import com.sf.job.ItemReader;
import com.sf.job.domain.JsonRecord;
import com.sf.util.json.JsonUtil;

public class JsonRecordItemReader implements ItemReader<String,JsonRecord> {

    private final JsonUtil jsonUtil = new JsonUtil();
    private final IdKey idKey;

    public JsonRecordItemReader(IdKey idKey){
        this.idKey = idKey;
    }

    @Override
    public JsonRecord readItem(String jsonString) {
        String id = jsonUtil.readJsonMap(jsonString).get(idKey);
        return new JsonRecord(id,jsonString);
    }

}
