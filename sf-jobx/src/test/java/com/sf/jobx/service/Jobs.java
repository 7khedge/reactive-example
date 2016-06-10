package com.sf.jobx.service;

import com.sf.jobx.adaptor.ItemAdaptor;
import com.sf.jobx.config.JsonRecordFileConfig;
import com.sf.jobx.domain.IdKey;
import com.sf.jobx.domain.JsonRecord;
import com.sf.util.file.FileUtil;

/**
 * Created by adityasofat on 21/03/2016.
 */
public class Jobs {

    public static JsonRecordFileConfig SNSApplicationInstance(ItemAdaptor<JsonRecord> jsonRecordItemAdaptor){
        return new JsonRecordFileConfig(new IdKey("id"), FileUtil.getClassPathInputStream("2_ApplicationInstance.json"), jsonRecordItemAdaptor);
    }
}
